package com.example.spaceflightnews.network.model

import com.google.gson.annotations.SerializedName

data class Launche(
    @SerializedName("id")
    val id: String,
    @SerializedName("provider")
    val provider: String
)