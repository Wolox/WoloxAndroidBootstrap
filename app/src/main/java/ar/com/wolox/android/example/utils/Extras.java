package ar.com.wolox.android.example.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

/**
 * Util class to store keys to use with {@link SharedPreferences} or as argument between
 * {@link Fragment} or {@link Activity}.
 */
public class Extras {

    private Extras() {}

    public static class UserLogin {
        public static final String USERNAME = "username";
    }
}
