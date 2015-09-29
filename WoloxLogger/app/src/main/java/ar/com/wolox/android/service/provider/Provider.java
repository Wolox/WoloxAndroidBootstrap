package ar.com.wolox.android.service.provider;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;

public interface Provider<T> {

    void provide(int currentPage, int itemsPerPage, Callback<List<T>> callback);

    List<T> getOfflineData(); // Return a empty list if the offline data is disable

    void saveOfflineData(Response apiResponse); // Remove previous data if is the first page

}
