package com.example.swipeassignment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipeassignment.models.ProductListItem
import com.google.android.material.imageview.ShapeableImageView

class ProductListItemAdapter ( var productList: List<ProductListItem>): RecyclerView.Adapter<ProductListItemAdapter.MyViewHolder>(){

    private var filteredList: List<ProductListItem> = productList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= productList[position]

        // Load product image using Glide
        if (!currentItem.product_image.isNullOrBlank()) {
            Glide.with(holder.itemView.context)
                .load(currentItem.product_image)
                .placeholder(R.drawable.default_image) // Placeholder image resource
                .error(R.drawable.error_image) // Error image resource if loading fails
                .into(holder.titleImage)
        } else {
            // If product_image is null or empty, set a placeholder or hide the ImageView
            holder.titleImage.setImageResource(R.drawable.default_image)
        }

        holder.product_name.text= currentItem.product_name
        holder.product_price.text= currentItem.product_price.toInt().toString()
        holder.product_tax.text= currentItem.product_tax.toInt().toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val product_name: TextView = itemView.findViewById(R.id.product_name)
        val product_price: TextView = itemView.findViewById(R.id.product_price)
        val product_tax: TextView = itemView.findViewById(R.id.product_tax)
    }

    fun updateData(newList: List<ProductListItem>) {
        productList = newList
        filterData("") // Reset filter when updating the dataset
        notifyDataSetChanged()
    }

    fun filterData(query: String) {
        Log.d("Filter", "Filtering with query: $query")
        filteredList = if (query.isEmpty()) {
            productList
        } else {
            productList.filter { product ->
                product.product_name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
        Log.d("Filter", "Filtered List: $filteredList")
    }
}