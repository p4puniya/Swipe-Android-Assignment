package com.example.swipeassignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.swipeassignment.api.ProductAPI
import com.example.swipeassignment.api.RetrofitHelper
import com.example.swipeassignment.models.ResponseFromURL
import com.example.swipeassignment.utils.NetworkConnection
import com.example.swipeassignment.viewmodels.MainViewModel
import com.example.swipeassignment.viewmodels.MainViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var fabOpenBottomSheetFragment: FloatingActionButton
    lateinit var bottomSheetFragment: AddProductFragment
    lateinit var productAPI: ProductAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository= (application as ProductApplication).productRepository

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        //Open Bottom Sheet fragment
        fabOpenBottomSheetFragment = findViewById(R.id.floating_action_button)
        fabOpenBottomSheetFragment.setOnClickListener{
            bottomSheetFragment= AddProductFragment()
            bottomSheetFragment.show(supportFragmentManager,"BSDialogFragment")
        }

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