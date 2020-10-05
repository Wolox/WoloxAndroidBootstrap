package ar.com.wolox.android.example.network.builder

import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Response

class NetworkBuilder<T> {

    private var onResponseSuccessful: (T?) -> Unit = {}
    private var onResponseFailed: (ResponseBody?, Int) -> Unit = { _, _ -> }
    private var onCallFailure: (Throwable?) -> Unit = {}

    fun onResponseSuccessful(block: (T?) -> Unit) = apply { onResponseSuccessful = block }
    fun onResponseFailed(block: (ResponseBody?, Int) -> Unit) = apply { onResponseFailed = block }
    fun onCallFailure(block: (Throwable?) -> Unit) = apply { onCallFailure = block }

    fun build(networkResponse: NetworkResponse<Response<T>>) {
        return when (networkResponse) {
            is NetworkResponse.Success -> onResponseSuccessful.invoke(networkResponse.response.body())
            is NetworkResponse.Error -> onResponseFailed.invoke(networkResponse.response.errorBody(), networkResponse.response.code())
            is NetworkResponse.Failure -> onCallFailure.invoke(networkResponse.t)
        }
    }
}

inline fun <T> networkRequest(response: NetworkResponse<Response<T>>, block: NetworkBuilder<T>.() -> Unit) =
        NetworkBuilder<T>().apply(block).build(response)