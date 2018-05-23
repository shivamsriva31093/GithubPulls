package com.meesho.task.githubpulls.data.remote.apiservice

import android.content.Context
import com.meesho.task.githubpulls.utils.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.HttpUrl
import android.R.attr.host
import com.meesho.task.githubpulls.utils.exceptions.NoNetworkException


class HttpCustomInterceptor(val context: Context) : Interceptor {

    private var networkMonitor: NetworkMonitor = NetworkMonitor(context)


    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkMonitor.isConnected())
            return handleIntercept(chain)
        throw NoNetworkException()
    }


    private fun handleIntercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return chain.proceed(request)
        /*if (!TmdbApi.API_BASE_URL.equals(request.url().host())) {
            return chain.proceed(request)
        }
        val urlBuilder = request.url().newBuilder()
        urlBuilder.setEncodedQueryParameter(TmdbApi.API_KEY, apiKey)
        val builder = request.newBuilder()
        builder.url(urlBuilder.build())
        return chain.proceed(builder.build())*/
    }
}