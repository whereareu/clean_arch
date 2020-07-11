package com.example.clean_arch.data.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieRemote(
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("image")
    val image: String,

    @Expose
    @SerializedName("title")
    val title: String
) {
}