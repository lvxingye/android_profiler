package com.example.profiler

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.Choreographer
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView

@SuppressLint("SetTextI18n")
class Profiler(application: Application) {
    private val mApplication=application
    private val DEBUG_TAG="profiler.main"
    private val choreographer: Choreographer=Choreographer.getInstance()
    private val lifecycleCallbacks: LifecycleCallbacks
    private val frameDataCallback:FrameDataCallback
    private var isEnabled=false
    private var tvFrameData: TextView?=null

    init {
        lifecycleCallbacks=LifecycleCallbacks{
            activity ->
            tvFrameData= TextView(activity)
            tvFrameData!!.setPadding(0,0,10,0)
            tvFrameData!!.textSize=20.0f
            tvFrameData!!.setTextColor(0xFF000000.toInt())
            tvFrameData!!.y=60.0f
            tvFrameData!!.gravity= Gravity.RIGHT or Gravity.TOP
            activity.window.decorView.findViewById<FrameLayout>(android.R.id.content).addView(tvFrameData)

        }
        frameDataCallback= FrameDataCallback{
            frameData ->
            if(tvFrameData !=null){
                val textFps= "FPS ${"%.1f".format(frameData.fps)}"
                val textDropRate= "Drop/s ${"%.1f".format(frameData.drop_rate)}"
                val textDropCount= "Drop ${"%d".format(frameData.drop_count)}"
                tvFrameData!!.text ="$textFps\n$textDropRate\n$textDropCount"
                Log.d(DEBUG_TAG,"$textFps $textDropRate $textDropCount")
            }else{
                Log.d(DEBUG_TAG,"Activity not start yet. Skipping capture frame data.")
            }
        }

        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)

    }
    fun start(){
        isEnabled=true
        frameDataCallback.setEnable(isEnabled)
        choreographer.postFrameCallback(frameDataCallback)
    }
    fun stop(){
        isEnabled=false
        frameDataCallback.setEnable(isEnabled)

    }
}