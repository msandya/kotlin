package com.example.retro.Common

import com.example.retro.Interface.RetrofitService
import com.example.retro.Retrofit.RetrofitClient

object Common {
    private var BASE_URL = "http://simplifiedcoding.net/demos/"
    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}