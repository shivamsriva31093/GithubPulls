package com.meesho.task.githubpulls.ui.homescreen

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.meesho.task.githubpulls.R
import com.meesho.task.githubpulls.utils.ActivityUtils

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_searchbar.*
import android.widget.EditText
import android.view.MotionEvent
import android.text.TextUtils
import com.meesho.task.githubpulls.utils.SnackbarUtils
import kotlinx.android.synthetic.main.content_main.*


class HomescreenActivity : AppCompatActivity() {

    private lateinit var presenter: HomeScreenContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)
        setUpSearchBox()

        val fragmentManager = supportFragmentManager
        var fragment:HomeScreenFragment? = fragmentManager.findFragmentByTag(HomeScreenFragment.TAG) as HomeScreenFragment?
        if(fragment == null) {
            fragment = HomeScreenFragment.newInstance(bundle = null)
        }
        ActivityUtils.addFragmentToActivity(fragmentManager, fragment, R.id.container, HomeScreenFragment.TAG)
        presenter = HomeScreenPresenter(fragment, applicationContext)
    }

    private fun setUpSearchBox() {

        searchInput.setOnTouchListener { _, _ ->
            searchInput.isFocusableInTouchMode = true
            searchInput.requestFocus()
            false
        }

        searchInput.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputMethodManager1 = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager1.hideSoftInputFromWindow(v.windowToken, 0)
                attemptToSearch(searchInput.text.toString())
            }
            false
        }
        backButton.setOnClickListener { /*finish()*/
            ActivityUtils.removeFragmentFromActivity(supportFragmentManager, HomeScreenFragment.TAG)
//            startActivity(intent)
            val fragment = HomeScreenFragment.newInstance(null)
            ActivityUtils.replaceFragmentFromActivity(supportFragmentManager, fragment, R.id.container)
            presenter = HomeScreenPresenter(fragment, applicationContext)
        }
    }

    private fun attemptToSearch(query: String) {
        if (TextUtils.isEmpty(query)) {
            SnackbarUtils.showSnack(coord_parent, R.string.input_a_query)
            return
        }
        val searchQuery = query.split(":")
        if(searchQuery.size == 2) {
            progressBar.visibility = View.VISIBLE
            presenter.getPullRequests(owner = searchQuery[0], repo = searchQuery[1])
            setInputMethodVisible(false)
        } else {
            SnackbarUtils.showSnack(coord_parent, R.string.not_in_required_format)
        }
    }

    private val showInputMethodRunnable = {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(searchInput, 0)
    }

    private fun setInputMethodVisible(hasFocus: Boolean) {
        if (hasFocus)
            Handler().post({showInputMethodRunnable})
        else {
            Handler().removeCallbacks({showInputMethodRunnable})
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(searchInput.windowToken, 0)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    view.isFocusable = false
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        searchInput.isFocusableInTouchMode = true
        searchInput.requestFocus()
        searchInput.setOnFocusChangeListener { _, hasFocus -> setInputMethodVisible(hasFocus) }
    }
}
