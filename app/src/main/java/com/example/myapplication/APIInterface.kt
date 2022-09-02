package com.example.myapplication


import retrofit2.Call

import retrofit2.http.GET




interface ApiInterface {
    @GET("hiring.json")
    fun getJsonData(): Call<List<JsonDataClass>>
}