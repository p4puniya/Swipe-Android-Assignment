package com.example.swipeassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.swipeassignment.utils.NetworkConnection
import com.example.swipeassignment.viewmodels.MainViewModel
import com.example.swipeassignment.viewmodels.MainViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var fabOpenBottomSheetFragment: FloatingActionButton
    lateinit var bottomSheetFragment: AddProductFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository= (application as ProductApplication).productRepository

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.products.observe(this, {
//            Log.d("View Model Works", it.toString())
//            Toast.makeText(this@MainActivity, it.size.toString(), Toast.LENGTH_SHORT).show()
        })

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