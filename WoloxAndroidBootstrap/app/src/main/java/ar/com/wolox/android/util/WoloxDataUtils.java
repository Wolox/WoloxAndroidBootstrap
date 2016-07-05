package ar.com.wolox.android.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.com.wolox.android.callback.WoloxCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class WoloxDataUtils {

    /**
     * The size of the cache specifies the amount of callbacks set to be saved on memory before
     * starting to delete the older ones.
     * The cache delta is the amount of callbacks to be erased.
     */
    private static final int DEFAULT_CACHE_SIZE = 50;
    private static final int DEFAULT_CACHE_DELTA = 10;

    private static Map<Class, Set<MethodCallbacks>> sCallbacks = new HashMap<>();
    private static List<MethodCallbacks> sOldestCallbacks = new LinkedList<>();
    private static int sCacheSize = DEFAULT_CACHE_SIZE;
    private static int sCacheDelta = DEFAULT_CACHE_DELTA;
    private static int sCallbacksAmount = 0;

    /**
     * Performs a request given a method of a Service, with the specific parameters. If an exactly
     * same request has already been made, it still calls the Callback given when the response
     * arrives.
     *
     * @param service    The service object (not the class) where the method is
     * @param methodName The name of the method to be called
     * @param cb         The callback to be executed after the request is made
     * @param parameters The parameters sent to the method
     * @return whether the request was performed or not
     */
    public static <T> boolean performRequest(Object service, String methodName, WoloxCallback cb,
                                             Object... parameters) {
        Class serviceClass = service.getClass();
        for (Method method : serviceClass.getMethods()) {
            if (method.getName().equals(methodName)) {
                //Add the sent Callback to the Queue
                Set<MethodCallbacks> methodCallbacksList = sCallbacks.get(serviceClass);
                if (methodCallbacksList == null) {
                    methodCallbacksList = new HashSet<>();
                    sCallbacks.put(serviceClass, methodCallbacksList);
                }
                MethodCallbacks methodCallbacks = new MethodCallbacks();
                methodCallbacks.service = serviceClass;
                methodCallbacks.method = method;
                methodCallbacks.parameters = parameters;
                boolean methodCallbacksFound = false;
                for (MethodCallbacks each : methodCallbacksList) {
                    if (each.equals(methodCallbacks)) {
                        methodCallbacks = each;
                        methodCallbacksFound = true;
                        break;
                    }
                }
                methodCallbacks.callbacks.add(cb);
                //If the MethodCallback is a new one, add it to the set where all the
                //methodcallbacks of the service are
                if (!methodCallbacksFound) {
                    methodCallbacksList.add(methodCallbacks);
                    sOldestCallbacks.add(methodCallbacks);
                }

                //If the maximum cache sized is reached, remove the oldest callbacks from the map
                //and from the list
                if (sCallbacksAmount++ == sCacheSize) {
                    Iterator<MethodCallbacks> iterator = sOldestCallbacks.iterator();
                    int i = 0;
                    while (iterator.hasNext() && i++ < sCacheDelta) {
                        MethodCallbacks oldestCallbacks = iterator.next();
                        sCallbacks.get(oldestCallbacks.service).remove(oldestCallbacks);
                        sCallbacksAmount -= oldestCallbacks.callbacks.size();
                        for (WoloxCallback callback : oldestCallbacks.callbacks) {
                            callback.onCallFailure(new Throwable());
                        }
                        iterator.remove();
                    }
                }

                //If the queue has other callbacks, then don't do the request again, the callback
                //will be called when the request returns
                if (methodCallbacks.callbacks.size() != 1) return true;

                //Alter it to handle caching
                final ArrayDeque<WoloxCallback> finalCallbacksQueue = methodCallbacks.callbacks;
                WoloxCallback<T> newCb = new WoloxCallback<T>() {
                    @Override
                    public void onSuccess(T response) {
                        while (!finalCallbacksQueue.isEmpty()) {
                            WoloxCallback cbToCall = finalCallbacksQueue.poll();
                            cbToCall.onSuccess(response);
                        }
                    }

                    @Override
                    public void onCallFailed(ResponseBody responseBody, int code) {
                        while (!finalCallbacksQueue.isEmpty()) {
                            WoloxCallback cbToCall = finalCallbacksQueue.poll();
                            cbToCall.onCallFailed(responseBody, code);
                        }
                    }

                    @Override
                    public void onCallFailure(Throwable t) {
                        while (!finalCallbacksQueue.isEmpty()) {
                            WoloxCallback cbToCall = finalCallbacksQueue.poll();
                            cbToCall.onCallFailure(t);
                        }
                    }
                };

                //Make the request
                try {
                    Call<T> call = (Call<T>) method.invoke(service, parameters);
                    call.enqueue(newCb);
                } catch (IllegalAccessException e) {
                    return false;
                } catch (InvocationTargetException e) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void setCacheSize(int sCacheSize) {
        WoloxDataUtils.sCacheSize = sCacheSize;
    }

    public static void setCacheDelta(int sCacheDelta) {
        WoloxDataUtils.sCacheDelta = sCacheDelta;
    }

    private static class MethodCallbacks {
        private Class service;
        private Method method;
        private Object[] parameters;
        private ArrayDeque<WoloxCallback> callbacks = new ArrayDeque<WoloxCallback>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MethodCallbacks that = (MethodCallbacks) o;

            if (!method.equals(that.method)) return false;
            //Compare all but the last parameter (the callback)
            for (int i = 0; i < that.parameters.length - 1; i++) {
                if (!that.parameters[i].equals(parameters[i])) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = method.hashCode();
            result = 31 * result + Arrays.hashCode(parameters);
            return result;
        }
    }
}