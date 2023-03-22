#!/usr/bin/env bash

adb shell am start -n com.example.demoapppodlodka.android/com.example.demoapppodlodka.android.MainActivity

./gradlew jsBrowserRun

./gradlew run