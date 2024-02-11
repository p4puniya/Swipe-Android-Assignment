package com.example.swipeassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductListItem(
    @PrimaryKey(autoGenerate = true)
    val product_Id: Int,
    val image: String?= null,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)