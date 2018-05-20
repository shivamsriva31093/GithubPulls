package com.meesho.task.githubpulls.ui.homescreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.meesho.task.githubpulls.R


@Suppress("JAVA_CLASS_ON_COMPANION")
class HomeScreenFragment : Fragment(), HomeScreenContract.View {
    lateinit var homeScreenPresenter: HomeScreenContract.Presenter

    override fun setPresenter(presenter: HomeScreenContract.Presenter) {
        this.homeScreenPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home_screen, container, false)
        return rootView
    }



    companion object {
        val TAG:String = HomeScreenFragment::class.java.simpleName

        fun newInstance(bundle: Bundle?) : HomeScreenFragment {
            val fragment = HomeScreenFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
