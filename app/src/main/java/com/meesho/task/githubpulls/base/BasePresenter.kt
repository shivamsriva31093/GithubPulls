package com.meesho.task.githubpulls.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

interface BasePresenter {
    val valveSource:PublishProcessor<Boolean>
    val disposableList: CompositeDisposable
    fun start(shouldStart:Boolean)
    fun destroy()
}