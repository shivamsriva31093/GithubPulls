package com.meesho.task.githubpulls.utils

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.content.Intent
import android.app.Activity
import android.support.v4.app.TaskStackBuilder


class ActivityUtils {
    companion object {
        /**
         * Enables back navigation for activities that are launched from the NavBar.
         * See `AndroidManifest.xml` to find out the parent activity names for each activity.
         * @param intent
         */
        fun createBackStack(activity: Activity, intent: Intent) {
            val builder = TaskStackBuilder.create(activity)
            builder.addNextIntentWithParentStack(intent)
            builder.startActivities()
        }

        fun addFragmentToActivity(
                @NonNull fragmentManager: FragmentManager,
                @NonNull fragment: Fragment,
                containerId: Int,
                tag: String) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            val transaction = fragmentManager.beginTransaction()
            transaction.add(containerId, fragment, tag)
            transaction.commit()
        }

        fun removeFragmentFromActivity(
                @NonNull fragmentManager: FragmentManager,
                tag: String
        ) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = fragmentManager.findFragmentByTag(tag)
            if(fragment != null) {
                transaction.remove(fragment)
                transaction.commit()
            }
        }

        fun replaceFragmentFromActivity(@NonNull fragmentManager: FragmentManager,
                                        @NonNull fragment: Fragment, containerId: Int) {
            checkNotNull(fragmentManager)
            checkNotNull(fragment)
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(containerId, fragment)
            transaction.commit()
        }
    }
}