package com.meesho.task.githubpulls.base

interface BaseView<in T> {
    fun setPresenter(presenter: T)
}