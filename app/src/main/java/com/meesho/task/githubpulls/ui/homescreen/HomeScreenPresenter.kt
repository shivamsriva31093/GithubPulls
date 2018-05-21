package com.meesho.task.githubpulls.ui.homescreen

import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.data.remote.apiservice.ApiManager
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.util.*


class HomeScreenPresenter : HomeScreenContract.Presenter {

    private var view: HomeScreenContract.View
    val TAG: String = HomeScreenPresenter::class.java.simpleName

    constructor(view: HomeScreenContract.View) {
        this.view = view
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
        val apiService = ApiManager().getGithubService()

        val pullRequests = apiService?.getPullRequests(repo, owner)

        pullRequests?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    view.showLoadingIndicator(false)
                    view.showPullRequestsList(it)
                }, {
                    it.printStackTrace()
                    view.showLoadingIndicator(false)
                    view.showErrorMessage(R.string.no_response)
                })

    }
}