package com.example.ecom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecom.model.product
import com.example.ecom.databinding.ProductItemBinding

class productAdapter(
    private val products : List<product>,
    private val clickListener: productClickListener
) : RecyclerView.Adapter<productViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(from, parent, false)
        return productViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        holder.bindProduct(products.get(position))
    }
}