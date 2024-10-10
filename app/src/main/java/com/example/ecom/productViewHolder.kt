package com.example.ecom

import com.example.ecom.databinding.ProductItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecom.model.product

class productViewHolder(
    private val productBinding : ProductItemBinding,
    private val clickListener: productClickListener) : RecyclerView.ViewHolder(productBinding.root){

    private val IMAGE_URL = "https://fakestoreapi.com/img/"
    fun bindProduct(Product: product){
        Glide.with(productBinding.root).load(Product.image).into(productBinding.productImage);
        productBinding.productName.text = Product.title
        productBinding.productPrice.text = Product.price

        productBinding.cardView.setOnClickListener{
            clickListener.onClick(Product)
        }
    }
}

