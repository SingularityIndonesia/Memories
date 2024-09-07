/*
 * Copyright (c) 2024 Singularity Indonesia
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "plugin.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.compose.gradle.plugin)
    implementation(libs.kotlin.allopen)
}

gradlePlugin {
    plugins {
        register("AppConventionV1") {
            id = "AppConventionV1"
            implementationClass = "plugin.convention.AppConventionV1"
        }
        register("LibraryConventionV1") {
            id = "LibraryConventionV1"
            implementationClass = "plugin.convention.LibraryConventionV1"
        }
        register("CompileIOS") {
            id = "CompileIOS"
            implementationClass = "plugin.convention.CompileIOS"
        }
        register("CompileWasm") {
            id = "CompileWasm"
            implementationClass = "plugin.convention.CompileWasm"
        }
        register("FeaturePane") {
            id = "FeaturePane"
            implementationClass = "plugin.convention.features.FeaturePane"
        }
        register("FeatureContextReceiver") {
            id = "FeatureContextReceiver"
            implementationClass = "plugin.convention.features.FeatureContextReceiver"
        }
        register("FeatureHttpClient") {
            id = "FeatureHttpClient"
            implementationClass = "plugin.convention.features.FeatureHttpClient"
        }
        register("FeatureSerialization") {
            id = "FeatureSerialization"
            implementationClass = "plugin.convention.features.FeatureSerialization"
        }
        register("FeatureAndroidPluto") {
            id = "FeatureAndroidPluto"
            implementationClass = "plugin.convention.features.FeatureAndroidPluto"
        }
        register("FeatureCoroutine") {
            id = "FeatureCoroutine"
            implementationClass = "plugin.convention.features.FeatureCoroutine"
        }
        register("ProjectConfig") {
            id = "ProjectConfig"
            implementationClass = "plugin.convention.ProjectConfig"
        }
        register("AndroidTest") {
            id = "AndroidTest"
            implementationClass = "plugin.convention.AndroidTest"
        }
    }
}
