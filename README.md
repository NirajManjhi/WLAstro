The project is basically an Android app which shows the daily Astronomy picture of the day

API used - GET https://api.nasa.gov/planetary/apod?api_key={your api key here}

Architecture - MVVM
Tech stack - Dagger2 + RxJava2 + RxAndroid + Retrofit + Glide + Jetpack

Improvement areas :
 - API key security
 - Gradle optimizations + prod/preprod configs
 - play video inside the app
 - BaseClasses for re-usability
 - Unit test
 - More cleaner architecture

 Note: I've mostly worked with MVP and I wanted to give MVVM a try, hence tried with what I understood. However there could be very minor enhancement needed here and there but due to time constraint I couldn't look for more (could have used coroutines but I'm more familiar with RxJava)