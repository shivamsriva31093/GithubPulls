package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName
data class ReviewComments(
    @SerializedName("href") val href: String
)