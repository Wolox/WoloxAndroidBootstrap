package ar.com.wolox.android.service.provider;

import java.util.List;

import retrofit.Callback;

public interface Provider<T> {

    void provide(int currentPage, int itemsPerPage, Callback<List<T>> callback);

}
