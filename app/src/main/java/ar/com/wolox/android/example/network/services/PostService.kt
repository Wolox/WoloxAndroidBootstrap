package ar.com.wolox.android.example.network.services

import ar.com.wolox.android.example.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post
}
