package com.meesho.task.githubpulls.ui.homescreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.data.models.api.PullRequests
import com.meesho.task.githubpulls.utils.LogUtils.LOGD
import com.meesho.task.githubpulls.utils.LogUtils.LOGE


@Suppress("JAVA_CLASS_ON_COMPANION")
class HomeScreenFragment : Fragment(), HomeScreenContract.View {
    override fun showLoadingIndicator(status: Boolean) {

    }

    override fun showPullRequestsList(requestsList: MutableList<PullRequests>) {
        LOGD(TAG, requestsList.size.toString())
    }

    override fun showErrorMessage(msg: Int) {
        LOGE(TAG, getString(msg))
    }

    private var homeScreenPresenter: HomeScreenContract.Presenter? = null
    private var ownerName:String = "googlesamples"
    private var repo:String = "android-architecture"

    override fun setPresenter(presenter: HomeScreenContract.Presenter) {
        this.homeScreenPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home_screen, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeScreenPresenter?.getPullRequests(owner = ownerName, repo = repo)
    }

    companion object {
        val TAG: String = HomeScreenFragment::class.java.simpleName

        fun newInstance(bundle: Bundle?): HomeScreenFragment {
            val fragment = HomeScreenFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
