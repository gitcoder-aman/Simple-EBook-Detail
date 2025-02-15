// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.0"
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}