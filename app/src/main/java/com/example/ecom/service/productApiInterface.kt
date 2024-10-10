package com.example.ecom.service

import com.example.ecom.model.product
import retrofit2.Call
import retrofit2.http.GET

interface productApiInterface {

    @GET("/products")
    fun getProductList(): Call<List<product>>
}