package com.example.swipeassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//
//class AddProductFragment : BottomSheetDialogFragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_product, container, false)
//    }
//
//}

class AddProductFragment : BottomSheetDialogFragment() {

    // ... other declarations

    private lateinit var spinnerProductType: Spinner
    private lateinit var editTextProductName: EditText
    private lateinit var editTextSellingPrice: EditText
    private lateinit var editTextTaxRate: EditText

    // ... other declarations

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        spinnerProductType = view.findViewById(R.id.spinnerProductType)
        editTextProductName = view.findViewById(R.id.editTextProductName)
        editTextSellingPrice = view.findViewById(R.id.editTextSellingPrice)
        editTextTaxRate = view.findViewById(R.id.editTextTaxRate)

        // ... other initialization

        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (validateFields()) {
                // Perform your action (e.g., submit data)
                showToast("Data Submitted Successfully :D ")
            }
        }

        // ... other setup

        return view
    }

    private fun validateFields(): Boolean {
        // Validate product type selection
        if (spinnerProductType.selectedItemPosition == 0) {
            showToast("Please select a product type")
            return false
        }

        // Validate non-empty product name
        val productName: String = editTextProductName.text.toString().trim()
        if (productName.isEmpty()) {
            showToast("Please enter a product name")
            return false
        }

        // Validate decimal numbers for selling price and tax
        try {
            val sellingPrice: Double = editTextSellingPrice.text.toString().toDouble()
            val taxRate: Double = editTextTaxRate.text.toString().toDouble()

            // You can add additional checks here if needed

        } catch (e: NumberFormatException) {
            showToast("Invalid selling price or tax rate")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // ... other methods
}
