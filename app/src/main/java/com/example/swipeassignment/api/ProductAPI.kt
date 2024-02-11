package com.example.swipeassignment.api

import com.example.swipeassignment.models.ProductListItem
import com.example.swipeassignment.models.ResponseFromURL
import com.example.swipeassignment.models.productDataClass
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductAPI {
    @GET("public/get")
    suspend fun getProducts() : List<ProductListItem>
    @FormUrlEncoded
    @POST("public/add")
    suspend fun getResponse(
        @Field("product_name") product_name: String,
        @Field("product_type") product_type: String,
        @Field("price") price: Double,
        @Field("tax") tax: Double
    ): Response<ResponseFromURL>
}