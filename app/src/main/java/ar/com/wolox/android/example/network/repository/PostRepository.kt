package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.network.services.PostService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val retrofitServices: RetrofitServices) {

    private val service: PostService
        get() = retrofitServices.getService(PostService::class.java)

    suspend fun getPostById(id: Int) = withContext(Dispatchers.IO) {
        service.getPostById(id)
    }
}