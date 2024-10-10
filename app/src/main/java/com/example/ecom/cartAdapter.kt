package com.example.ecom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecom.model.product
import com.example.ecom.databinding.ProductCartBinding

class cartAdapter(
    private val products : List<product>
) : RecyclerView.Adapter<cartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductCartBinding.inflate(from, parent, false)
        return cartViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: cartViewHolder, position: Int) {
        holder.bindProduct(products.get(position))
    }
}