package com.example.ecom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ecom.databinding.ActivityDetailBinding
import com.example.ecom.model.product
import com.example.ecom.model.productList
import com.google.gson.Gson

class detailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product_Title = intent.getStringExtra("name")
        val product_Description = intent.getStringExtra("description")
        val product_Category = intent.getStringExtra("category")
        val product_Image = intent.getStringExtra("image")
        val product_Price = intent.getStringExtra("price")

        binding.productName.text = product_Title
        binding.productPrice.text = "price: "+product_Price
        binding.productDescription.text = product_Description
        binding.productCategory.text = "Category: "+product_Category
        Glide.with(binding.root).load(product_Image).into(binding.productImage);

        binding.addTocart.setOnClickListener{
            val product = product("",product_Title,product_Price,"",product_Image,"","","")
            productList.add(product)
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()

            myEdit.putString("name", product_Title)
            myEdit.putString("price", product_Price)
            myEdit.putString("image", product_Image)
            val gson = Gson()
            val json:String = gson.toJson(productList)
            myEdit.putString("LIST",json)
            myEdit.apply()

            onBackPressed();
        }

    }

  }
