package com.example.swipeassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.swipeassignment.models.ProductListItem

@Dao
interface ProductDAO {

    @Insert
    suspend fun addProducts(products: List<ProductListItem>)

    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<ProductListItem>
}