package com.example.swipeassignment.models

class ResponseFromURL (
        val message: String,
        val product_details: ProductListItem,
        val product_id: Int,
        val success: Boolean
        )