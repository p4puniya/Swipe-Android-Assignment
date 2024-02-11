package com.example.swipeassignment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.swipeassignment.api.RetrofitHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class AddProductFragment : BottomSheetDialogFragment() {

    //variables
    private lateinit var spinnerProductType: Spinner
    private lateinit var editTextProductName: EditText
    private lateinit var editTextSellingPrice: EditText
    private lateinit var editTextTaxRate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        spinnerProductType = view.findViewById(R.id.spinnerProductType)
        editTextProductName = view.findViewById(R.id.editTextProductName)
        editTextSellingPrice = view.findViewById(R.id.editTextSellingPrice)
        editTextTaxRate = view.findViewById(R.id.editTextTaxRate)

        //Check and Submit Data Using validateFields() and submitData()
        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (validateFields()) {
                submitData()
            }
        }
        return view
    }

    private fun submitData() {
        val productName: String = editTextProductName.text.toString().trim()
        val productType: String = spinnerProductType.selectedItemPosition.toString().trim()
        val sellingPrice: Double = editTextSellingPrice.text.toString().toDouble()
        val taxRate: Double = editTextTaxRate.text.toString().toDouble()

        val result= RetrofitHelper.create()
        lifecycleScope.launch{
            try {
                val call = result.getResponse(productName,productType,sellingPrice,taxRate)
                Log.d("Response from URL", call.toString())
                    showToast("Data Submitted Successfully")
                    showToast("Reload the app to see changes")
            }catch (e: Exception){
                Log.d("Response from URL", "FAILURE")
                showToast("Data Submission Failed.")
            }
        }
    }

    private fun validateFields(): Boolean {
        if (spinnerProductType.selectedItemPosition == 0) {
            showToast("Please select a product type")
            return false
        }
        val productName: String = editTextProductName.text.toString().trim()
        if (productName.isEmpty()) {
            showToast("Please enter a product name")
            return false
        }
        try {
            val sellingPrice: Double = editTextSellingPrice.text.toString().toDouble()
            val taxRate: Double = editTextTaxRate.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            showToast("Invalid selling price or tax rate")
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
