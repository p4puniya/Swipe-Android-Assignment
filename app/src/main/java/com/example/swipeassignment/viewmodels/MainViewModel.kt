package com.example.swipeassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeassignment.models.ProductListItem
import com.example.swipeassignment.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: ProductRepository): ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts()
        }
    }
    //for spinner in page 2
    val selectedProductType = MutableLiveData<String>()
        val products: LiveData<List<ProductListItem>>
        get()= repository.products

}