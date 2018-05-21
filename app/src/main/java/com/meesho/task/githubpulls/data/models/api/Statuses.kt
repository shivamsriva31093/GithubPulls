package com.meesho.task.githubpulls.data.models.api

import com.google.gson.annotations.SerializedName
data class Statuses(
    @SerializedName("href") val href: String
)