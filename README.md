A simple FPS monitor and ANR catcher for Android. Inspired by TinyDancer.  

How to use:  
Fisrt put the aar library to the `libs` subdirectory and then add these lines to your `build.gradle` file and re-sync:  
```
implementation files('libs/profiler-debug.aar')
```
Finally add these lines to your project:  
```
import com.example.profiler.Profiler
val profiler=Profiler()
profiler.start()
```
And then FPS should be shown at the top right corner.  

API ref:  
`class Profiler`: Base class used in your app.  
`profiler.start()`: Start monitoring.  
`profiler.stop()`: Stsop monitoring.  

Tips:
All files are in sub directory named `profiler`. And nothind in the sub directory `app`.   
And please use the `Assemble Project` command from `build` to generate arr library(will be generated in the subdirectory `profiler/build/outputs/aar`).  
