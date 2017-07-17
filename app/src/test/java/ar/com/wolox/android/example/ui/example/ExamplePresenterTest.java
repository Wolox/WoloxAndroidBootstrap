package ar.com.wolox.android.example.ui.example;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.com.wolox.android.example.utils.Extras;
import ar.com.wolox.wolmo.core.util.StorageUtils;

import org.junit.Before;
import org.junit.Test;

public class ExamplePresenterTest {

    private IExampleView mExampleView;
    private ExamplePresenter mExamplePresenter;
    private StorageUtils mStorageUtils;

    @Before
    public void createInstances() {
        mExampleView = mock(IExampleView.class);
        mStorageUtils = mock(StorageUtils.class);
        mExamplePresenter = new ExamplePresenter(mStorageUtils);
    }

    @Test
    public void usernameIsStored() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mStorageUtils, times(1)).storeInSharedPreferences(matches(Extras.UserLogin.USERNAME), matches("Test"));
    }

    @Test
    public void storeUsernameUpdatesView() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mExampleView, times(1)).onUsernameSaved();
    }

}
