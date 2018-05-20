package com.meesho.task.githubpulls.ui.homescreen

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

class HomeScreenPresenter : HomeScreenContract.Presenter {

    private lateinit var view:HomeScreenContract.View

    constructor(view:HomeScreenContract.View) {
        this.view = view
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
}