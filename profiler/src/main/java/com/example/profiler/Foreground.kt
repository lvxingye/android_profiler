package com.example.profiler

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView

class LifecycleCallbacks(callbacks: (Activity)-> Unit={}): Application.ActivityLifecycleCallbacks {

    private val mCallbacks=callbacks
    override fun onActivityResumed(activity: Activity) {
        mCallbacks(activity)
    }

    //not used
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
}


