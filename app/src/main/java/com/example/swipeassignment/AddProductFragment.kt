package com.example.swipeassignment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.swipeassignment.viewmodels.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddProductFragment : BottomSheetDialogFragment() {

    //Variables
    private lateinit var mainViewModel: MainViewModel
    private lateinit var productTypeSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        productTypeSpinner = view.findViewById(R.id.spinnerProductType)

        // Initialize ViewModel
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        // Set up Spinner with an ArrayAdapter and product types
        val productTypes = arrayOf("Type1", "Type2", "Type3") // Add your actual product types
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, productTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productTypeSpinner.adapter = adapter

        // Set an item selected listener to update ViewModel when a type is selected
        productTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedType = productTypes[position]
                mainViewModel.selectedProductType.value = selectedType
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle nothing selected if needed
            }
        }

        return view
    }


}