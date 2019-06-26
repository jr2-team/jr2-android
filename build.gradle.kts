buildscript {
    val kotlinVersion = "1.3.40"
    val navigationVersion = "2.1.0-alpha05"

    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}