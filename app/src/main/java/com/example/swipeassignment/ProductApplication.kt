package com.example.swipeassignment

import android.app.Application
import com.example.swipeassignment.api.RetrofitHelper
import com.example.swipeassignment.db.ProductDatabase
import com.example.swipeassignment.repository.ProductRepository

class ProductApplication:Application() {

    lateinit var productRepository: ProductRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val gettingProductApi= RetrofitHelper.create()
        val database = ProductDatabase.getDatabase(applicationContext)
        productRepository = ProductRepository(gettingProductApi, database, applicationContext)

    }
}