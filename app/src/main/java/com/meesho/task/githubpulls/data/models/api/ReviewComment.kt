package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName
data class ReviewComment(
    @SerializedName("href") val href: String
)