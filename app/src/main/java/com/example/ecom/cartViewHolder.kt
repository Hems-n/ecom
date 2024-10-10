package com.example.ecom

import com.example.ecom.databinding.ProductItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecom.databinding.ProductCartBinding
import com.example.ecom.model.product

class cartViewHolder(
    private val productBinding : ProductCartBinding) : RecyclerView.ViewHolder(productBinding.root){

    fun bindProduct(Product: product){
        Glide.with(productBinding.root).load(Product.image).into(productBinding.productImage);
        productBinding.productName.text = Product.title
        productBinding.productPrice.text = "Price: "+Product.price
    }
}