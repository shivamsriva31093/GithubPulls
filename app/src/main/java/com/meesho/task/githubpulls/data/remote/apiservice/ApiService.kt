package com.meesho.task.githubpulls.data.remote.apiservice

import com.meesho.task.githubpulls.GITHUB_PULL_REQUESTS
import com.meesho.task.githubpulls.data.models.api.PullRequests
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(GITHUB_PULL_REQUESTS)
    fun getPullRequests(
            @Path("repo") repo:String,
            @Path("owner") owner:String,
            @Query("state")state:String? = "open"
    ): Flowable<MutableList<PullRequests>>
}