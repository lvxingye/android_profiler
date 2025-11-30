A simple FPS monitor and ANR catcher for Android. Inspired by TinyDancer.<\br>

How to use:
Fisrt put the aar library to the `libs` subdirectory and then add these lines to your `build.gradle` file and re-sync:<\br>
```
implementation files('libs/profiler-debug.aar')
```
Finally add these lines to your project:<\br>
```
import com.example.profiler.Profiler
val profiler=Profiler()
profiler.start()
```
And then FPS should be shown at the top right corner.<\br>

API ref:
`class Profiler`: Base class used in your app.<\br>
`profiler.start()`: Start monitoring.<\br>
`profiler.stop()`: Stsop monitoring.<\br>

Tips:
All files are in sub directory named `profiler`. And nothind in the sub directory `app`. <\br>
And please use the `Assemble Project` command from `build` to generate arr library(will be generated in the subdirectory `profiler/build/outputs/aar`).<\br>
