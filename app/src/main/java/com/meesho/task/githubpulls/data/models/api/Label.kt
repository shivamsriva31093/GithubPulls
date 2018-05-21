package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName
data class Label(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("color") val color: String,
    @SerializedName("default") val default: Boolean
)