package com.piyush.a47_retrofit_demo

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<List<Album>>
}
