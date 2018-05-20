package com.meesho.task.githubpulls.data.remote.apiservice

import com.meesho.task.githubpulls.API_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


open class ApiManager {
    companion object {
        val TAG = ApiManager::class.java.simpleName
        private lateinit var retrofit: Retrofit
        private lateinit var okHttpClient: OkHttpClient
    }

    protected fun getOkHttpClient(): OkHttpClient {
        return okHttpClient
    }

    protected fun setOkHttpClient(client: OkHttpClient) {
        okHttpClient = client
    }

    private fun retrofitBuilder(): Retrofit.Builder {
        val builder = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
        val clientBuilder = okHttpClient().newBuilder()
        configOkHttpClient(clientBuilder)
        return builder.client(clientBuilder.build())

    }

    @Synchronized
    protected fun okHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    protected fun configOkHttpClient(builder: OkHttpClient.Builder) {

    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build()
        }
        return retrofit
    }

    fun getGithubService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}