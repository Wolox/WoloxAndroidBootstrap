package ar.com.wolox.android.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import ar.com.wolox.android.WoloxApplication;

public class ToastUtils {

    public static void showToast(@NonNull Context context, @StringRes int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@NonNull Context context, @NonNull String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(@NonNull Context context, @StringRes int resId, int toastLength) {
        Toast.makeText(context, resId, toastLength).show();
    }

    public static void showToast(@NonNull Context context, @NonNull String text, int toastLength) {
        Toast.makeText(context, text, toastLength).show();
    }

    public static void showToast(@StringRes int resId) {
        showToast(WoloxApplication.context(), resId);
    }

    public static void showToast(@NonNull String text) {
        showToast(WoloxApplication.context(), text);
    }

    public static void showToast(@StringRes int resId, int toastLength) {
        showToast(WoloxApplication.context(), resId, toastLength);
    }

    public static void showToast(@NonNull String text, int toastLength) {
        showToast(WoloxApplication.context(), text, toastLength);
    }
}
