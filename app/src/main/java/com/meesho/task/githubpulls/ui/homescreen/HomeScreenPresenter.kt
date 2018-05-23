package com.meesho.task.githubpulls.ui.homescreen

import android.content.Context
import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.data.remote.apiservice.ApiManager
import com.meesho.task.githubpulls.utils.exceptions.NoNetworkException
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.net.HttpURLConnection
import java.util.*


class HomeScreenPresenter : HomeScreenContract.Presenter {

    private var view: HomeScreenContract.View
    private var context: Context
    val TAG: String = HomeScreenPresenter::class.java.simpleName

    constructor(view: HomeScreenContract.View, context: Context) {
        this.view = view
        this.context = context
        view.setPresenter(this)
    }

    override val valveSource: PublishProcessor<Boolean>
        get() = PublishProcessor.create()

    override val disposableList: CompositeDisposable
        get() = CompositeDisposable()

    override fun start(shouldStart: Boolean) {
        valveSource.onNext(shouldStart)
    }

    override fun destroy() {
        valveSource.onNext(false)
        disposableList.clear()
    }

    override fun getPullRequests(owner: String, repo: String) {
        val apiService = ApiManager(context).getGithubService()
        val pullRequests = apiService.getPullRequests(repo, owner)

        pullRequests
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view.showLoadingIndicator(false)
                    view.showPullRequestsList(it)
                }, {
                    view.showLoadingIndicator(false)
                    if(it is NoNetworkException) {
                        view.showErrorMessage(R.string.no_internet)
                    } else if (it is HttpException && it.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                        view.showErrorMessage(R.string.invalid_request)
                    } else {
                        view.showErrorMessage(R.string.no_response)
                    }
                })

    }
}