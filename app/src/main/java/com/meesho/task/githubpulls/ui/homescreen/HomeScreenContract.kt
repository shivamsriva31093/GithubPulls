package com.meesho.task.githubpulls.ui.homescreen

import com.meesho.task.githubpulls.base.BasePresenter
import com.meesho.task.githubpulls.base.BaseView

interface HomeScreenContract {
    interface View:BaseView<Presenter> {}
    interface Presenter:BasePresenter {}
}