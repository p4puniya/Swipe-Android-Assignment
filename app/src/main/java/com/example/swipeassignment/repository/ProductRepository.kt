package com.example.swipeassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swipeassignment.models.ProductListItem
import com.example.swipeassignment.api.ProductAPI
import com.example.swipeassignment.db.ProductDatabase
import com.example.swipeassignment.utils.NetworkUtils

class ProductRepository(
    private val productApi: ProductAPI,
    val productDatabase: ProductDatabase,
    private val applicationContext: Context
){

    private val productsLiveData = MutableLiveData<List<ProductListItem>>()
    val products: LiveData<List<ProductListItem>>
        get()= productsLiveData

    suspend fun getProducts(){

        if(NetworkUtils.isOnline(applicationContext)){
            val result= productApi.getProducts()
            if(result!=null) {
                productDatabase.productDao().addProducts(result)
                productsLiveData.postValue(result)
            }
        }else{
            val products = productDatabase.productDao().getProducts()
//            val productList= ProductListItem(1, null, 10.0, "Kambal", "Clothes", 10.0)
            productsLiveData.postValue(products)
        }

    }
}