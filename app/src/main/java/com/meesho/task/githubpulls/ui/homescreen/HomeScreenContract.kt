package com.meesho.task.githubpulls.ui.homescreen

import android.support.annotation.StringRes
import com.meesho.task.githubpulls.base.BasePresenter
import com.meesho.task.githubpulls.base.BaseView
import com.meesho.task.githubpulls.data.models.api.PullRequests

interface HomeScreenContract {
    interface View:BaseView<Presenter> {
        fun showLoadingIndicator(status:Boolean)
        fun showPullRequestsList(requestsList:MutableList<PullRequests>)
        fun showErrorMessage(@StringRes msg:Int)
    }
    interface Presenter:BasePresenter {
        fun getPullRequests(owner:String, repo:String)
    }
}