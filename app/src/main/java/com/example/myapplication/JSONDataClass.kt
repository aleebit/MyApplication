package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class JsonDataClass(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("listId") var listId: Int? = 0,
    @SerializedName("name") var name: String? = ""
)