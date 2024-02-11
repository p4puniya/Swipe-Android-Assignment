package com.example.swipeassignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeassignment.api.RetrofitHelper
import com.example.swipeassignment.repository.ProductRepository
import com.example.swipeassignment.utils.NetworkUtils
import com.example.swipeassignment.viewmodels.MainViewModel
import com.example.swipeassignment.viewmodels.MainViewModelFactory
import com.google.android.material.color.ThemeUtils

class ProductListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var productListAdapter: ProductListItemAdapter
    private lateinit var connectivityStatus: TextView
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_product_list, container, false)
        connectivityStatus= view.findViewById(R.id.connectivityStatus)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)
        connectivityStatus = view.findViewById(R.id.connectivityStatus)
        searchView = view.findViewById(R.id.searchView)
        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productListAdapter = ProductListItemAdapter(emptyList()) // Initially, pass an empty list
        recyclerView.adapter = productListAdapter

        updateData()

//        // Handle SearchView events
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.d("SearchView", "Query Submitted: $query")
//                // Handle query submission if needed
//                return true
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//                Log.d("SearchView", "Text changed: $newText")
//                // Filter data based on the search query
//                productListAdapter.filterData(newText.orEmpty())
//                return true
//            }
//        })

        // Retrieve the boolean value from the arguments
        val booleanValue = arguments?.getBoolean("BOOLEAN_KEY", false) ?: false

        // Now you can use the boolean value in the fragment
        if (booleanValue) {
            connectivityStatus.visibility = View.INVISIBLE
        } else {
            connectivityStatus.visibility= View.VISIBLE
        }
    }

    public fun updateData() {

        // Observe LiveData in ViewModel
        val repository = (requireActivity().application as ProductApplication).productRepository
        val mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)

        mainViewModel.products.observe(viewLifecycleOwner, { productList ->
            productListAdapter.updateData(productList)
        })
    }

    private fun searchView(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SearchView", "Query Submitted: $query")
                // Handle query submission if needed
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("SearchView", "Text changed: $newText")
                // Filter data based on the search query
                productListAdapter.filterData(newText.orEmpty())
                return true
            }
        })
    }
}