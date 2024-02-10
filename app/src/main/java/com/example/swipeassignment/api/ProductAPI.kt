package com.example.swipeassignment.api

import com.example.swipeassignment.models.ProductListItem
import retrofit2.http.GET

interface ProductAPI {
    @GET("public/get")
    suspend fun getProducts() : List<ProductListItem>
}