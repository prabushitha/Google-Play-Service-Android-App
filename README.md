# Google Play Service with Android Application

[![N|Solid](https://androidtv.news/wp-content/uploads/2015/09/google-play-games.png)](https://nodesource.com/products/nsolid)

We can add Google play service to your android application in easily with following steps.

  - Create account and app in Google Play Developer Console
  - Put BaseGameUtils folder to your app root
  - Edit your project's Manifest, layout and Activity with some codes

# Edit project's Manifest!
Add following code.
```sh
<meta-data
 android:name="com.google.android.gms.games.APP_ID"
 android:value="@string/app_id" />
<meta-data
 android:name="com.google.android.gms.version"
 android:value="@integer/google_play_services_version" />
```

Add following to String.xml file in Layout folder...

```sh
<string name="app_id">YOUR APP ID</string>
<string name="number_guesses_leaderboard">YOUR LEADERBOARD ID</string>
```
# Edit project's Gradle files!

add following to setting.gradle file
```sh
include ':BaseGameUtils'
```
add following to build.gradle file
```sh
dependencies {
    compile 'com.google.android.gms:play-services:+'
    compile project(':BaseGameUtils')
}
```

Now basic requirements are done. Check the the example code to how edit Activity.java file and layout.xml file for get service in Google Play Service.
