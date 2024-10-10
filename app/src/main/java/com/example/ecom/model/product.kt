package com.example.ecom.model

val PRODUCT_ID_EXTRA = "productExtra"
val productList = mutableListOf<product>()
data class product(
    val id : String ?,
    val title : String ?,
    val price : String ?,
    val description : String ?,
    val image : String ?,
    val category: String ?,
    val rate: String ?,
    val count: String ?,
)