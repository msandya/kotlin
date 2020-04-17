package com.sandya.demo


import com.sandya.demo.model.ProductFamily
import retrofit2.Call
import retrofit2.http.GET

interface NetWorkApi {

    @GET("/")
    fun getProducts(): Call<List<ProductFamily>>

}