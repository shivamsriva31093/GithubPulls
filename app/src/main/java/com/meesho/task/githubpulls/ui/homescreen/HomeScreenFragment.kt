package com.meesho.task.githubpulls.ui.homescreen


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife

import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.data.models.api.PullRequests
import com.meesho.task.githubpulls.utils.LogUtils.LOGD
import com.meesho.task.githubpulls.utils.LogUtils.LOGE
import com.meesho.task.githubpulls.utils.SnackbarUtils
import com.wang.avi.AVLoadingIndicatorView


@Suppress("JAVA_CLASS_ON_COMPANION")
class HomeScreenFragment : Fragment(), HomeScreenContract.View {

    @BindView(R.id.recView)lateinit var recView: RecyclerView

    private var progressBar:AVLoadingIndicatorView? = null
    private var placeholder:ConstraintLayout? = null

    override fun showLoadingIndicator(status: Boolean) {
        if(status) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun showPullRequestsList(requestsList: MutableList<PullRequests>) {
        LOGD(TAG, requestsList.size.toString())
        if(requestsList.isEmpty()) {
            SnackbarUtils.showSnack(parentLayout, R.string.no_pull_requests)
        } else {
            adapter.updateData(requestsList)
        }
    }

    override fun showErrorMessage(msg: Int) {
        LOGE(TAG, getString(msg))
        Snackbar.make(parentLayout,msg, Snackbar.LENGTH_INDEFINITE).show()
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
        ButterKnife.bind(this, rootView)
        setUpRecView()
        return rootView
    }

    private lateinit var adapter: RecViewAdapter

    private fun setUpRecView() {
        val layoutManager:LinearLayoutManager = LinearLayoutManager(activity)

        adapter = RecViewAdapter(mutableListOf())

        recView.layoutManager = layoutManager
        recView.adapter = adapter

    }

    private lateinit var parentLayout: CoordinatorLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        homeScreenPresenter?.getPullRequests(owner = ownerName, repo = repo)
        parentLayout = activity?.findViewById(R.id.coord_parent) as CoordinatorLayout
        progressBar = activity?.findViewById(R.id.progressBar) as AVLoadingIndicatorView
        placeholder = activity?.findViewById(R.id.placeholder) as ConstraintLayout
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
