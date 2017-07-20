package ar.com.wolox.android.example.utils;

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope;
import ar.com.wolox.wolmo.core.util.StorageUtils;

import javax.inject.Inject;

@ApplicationScope
public class UserUtils {

    private StorageUtils mStorageUtils;
    private String mUsername;

    @Inject
    public UserUtils(StorageUtils storageUtils) {
        mStorageUtils = storageUtils;
    }

    public void setUsername(String username) {
        mUsername = username;
        mStorageUtils.storeInSharedPreferences(Extras.UserLogin.USERNAME, username);
    }

    public String getUsername() {
        // Really, we don't need to query the username because this instance live as long as the
        // application, but we should add a check in case Android decides to kill the application
        // and return to a state where this isn't initialized.
        if (mUsername == null) {
            mUsername = mStorageUtils.getStringFromSharedPreferences(Extras.UserLogin.USERNAME, null);
        }
        return mUsername;
    }
}
