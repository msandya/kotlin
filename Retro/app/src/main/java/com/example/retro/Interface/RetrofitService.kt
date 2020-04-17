package com.example.retro.Interface

import com.example.retro.Model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}