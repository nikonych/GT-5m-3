package com.example.gt_5m_3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImage(
        @Query("q") keyWord: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 5,
        @Query("key") key: String  = "34844858-ddea8d8225426e2877a4191e5",
    ): Call<PixaModel>
}