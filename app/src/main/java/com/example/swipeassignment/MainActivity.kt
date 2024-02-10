package com.example.swipeassignment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeassignment.api.RetrofitHelper
import com.example.swipeassignment.models.ProductListItem
import com.example.swipeassignment.repository.ProductRepository
import com.example.swipeassignment.utils.NetworkConnection
import com.example.swipeassignment.utils.NetworkUtils
import com.example.swipeassignment.viewmodels.MainViewModel
import com.example.swipeassignment.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: List<ProductListItem>

    lateinit var productImage: String
    lateinit var productName: String
    lateinit var productPrice: String
    lateinit var productTax: String

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository= (application as ProductApplication).productRepository

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.products.observe(this, {
//            Log.d("View Model Works", it.toString())
//            Toast.makeText(this@MainActivity, it.size.toString(), Toast.LENGTH_SHORT).show()
        })


        //Network Connection Display
        val networkConnection= NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->
            val fragment = ProductListFragment()
            val bundle = Bundle()
            bundle.putBoolean("BOOLEAN_KEY", isConnected)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }


    }

}