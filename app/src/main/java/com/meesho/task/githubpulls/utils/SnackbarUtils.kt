package com.meesho.task.githubpulls.utils

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import com.meesho.task.githubpulls.R

class SnackbarUtils {
   companion object {
       fun showSnack(view: View, @StringRes msgId:Int) {
           Snackbar
                   .make(view, msgId, Snackbar.LENGTH_INDEFINITE)
                   .show()
       }
   }
}