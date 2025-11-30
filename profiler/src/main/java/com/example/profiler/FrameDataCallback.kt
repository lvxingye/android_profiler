package com.example.profiler

import android.view.Choreographer

class FrameDataCallback(callback:(FrameData)-> Unit= {}): Choreographer.FrameCallback{
    private val NANO_SEC_TO_MILL = 1000000
    private val MILL_SEC_TO_SEC = 1000
    private val REFRESH_RATE = 60.0
    inner class Calculation{
        private val VSYNC_PEROID_MILLS = MILL_SEC_TO_SEC/REFRESH_RATE

        private var lastFrameTimeNanos: Long=0
        private var lastDropTimeNanos: Long=0
        private var dropCounts_1sec: Long=0
        private var dropCounts: Long=0

        fun calculateFrameData(
            frameTimeNanos: Long,
            callback: (FrameData) -> Unit
        ): FrameData {
            val frameData: FrameData = FrameData()
            val duration=frameTimeNanos-lastFrameTimeNanos
            val dropDuration=frameTimeNanos-lastDropTimeNanos

            //calculation
            if(duration>(VSYNC_PEROID_MILLS*NANO_SEC_TO_MILL*1).toInt()){//drop n frames;n=1
                dropCounts_1sec++
                dropCounts++
            }
            frameData.fps=NANO_SEC_TO_MILL*MILL_SEC_TO_SEC*1.0/duration
            frameData.drop_rate=dropCounts_1sec*1.0*MILL_SEC_TO_SEC*NANO_SEC_TO_MILL/dropDuration
            frameData.drop_count=dropCounts
            if(dropDuration>=MILL_SEC_TO_SEC*NANO_SEC_TO_MILL*1){//frame drop rate per sec
                lastDropTimeNanos=frameTimeNanos
                dropCounts_1sec=0
            }
            lastFrameTimeNanos=frameTimeNanos

            //data return
            callback(frameData)
            return frameData//left for chain operations
        }
    }
    private val mCallback= callback
    private val calculation= Calculation()
    private var isEnable= false
    private var lastTime_1_sec:Long= 0

    fun setEnable(enable: Boolean){
        isEnable=enable
    }

    override fun doFrame(frameTimeNanos: Long) {
        if(!isEnable){
            return
        }

        calculation.calculateFrameData(frameTimeNanos){ frameData ->
            if( (frameTimeNanos-lastTime_1_sec) >= MILL_SEC_TO_SEC*NANO_SEC_TO_MILL){
                mCallback(frameData)
                lastTime_1_sec=frameTimeNanos
            }
        }

        //register for next frame
        Choreographer.getInstance().postFrameCallback (this )
    }

}
