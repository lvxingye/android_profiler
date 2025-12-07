A simple FPS monitor and ANR catcher for Android. Inspired by TinyDancer.  
ANR catcher uses library [xCrash](https://github.com/iqiyi/xCrash/tree/master) from [iqiyi](https://github.com/iqiyi).

How to use:  
First put the aar library to the `libs` subdirectory and then add these lines to your `build.gradle` file and re-sync:  
```
implementation files('libs/profiler-debug.aar')
implementation 'com.iqiyi.xcrash:xcrash-android-lib:3.0.0'
```
Finally add these lines to your project:  
```
import com.example.profiler.Profiler
val profiler=Profiler()
profiler.start()
```
And then FPS should be shown at the top right corner.  

If you want to stop monitoring, use `profiler.stop()`.

API ref:  
`class Profiler`: Base class used in your app. Use `Profiler()` to create new instance and init profiler. And xCrash will also be initialized here.
`profiler.start()`: Start monitoring.  
`profiler.stop()`: Stop monitoring.  

Tips:
All files are in sub directory named `profiler`. And nothing in the sub directory `app`.   
And please use the `Assemble Project` command from `build` to generate arr library( will be generated in the subdirectory `profiler/build/outputs/aar` ).  
When crash or ANR happened, xCrash will automatically dump crash information to `"files/tombstones"` folder under app root directory.  