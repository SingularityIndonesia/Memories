/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
import plugin.convention.companion.ReleaseNote
import plugin.convention.companion.Shared
import plugin.convention.companion.System
import plugin.convention.companion.cfg
import plugin.convention.companion.model
import plugin.convention.companion.presentation

plugins {
    id("AppConventionV1")
    id("CompileIOS")
    // id("CompileWasm")
    id("FeatureCoroutine")
    id("FeaturePane")
    id("FeatureSerialization")
    id("FeatureHttpClient")
    id("FeatureAndroidPluto")
    id("FeatureContextReceiver")
    id("ProjectConfig")
}

val ReleaseNote =
    "ReleaseNote.md"
        .let {
            val file = File(project.projectDir, it)
            ReleaseNote(file)
        }

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.material)
        }
        commonMain.dependencies {
            System("core")
            Shared("common")
            Shared("webclient")
            Shared("bottomsheetflow")

            presentation("authentication")
            model("authentication")
            presentation("dashboard")
            model("dashboard")
        }
    }
}

android {
    namespace = "com.singularity.basemobile"

    defaultConfig {
        resValue("string", "app_name", cfg("APP_NAME").replace("\"", ""))

        applicationId = cfg("APP_ID").replace("\"", "")
        versionNameSuffix = cfg("APP_VERSION_NAME_SUFFIX").replace("\"", "")
        applicationIdSuffix = cfg("APP_ID_SUFFIX").replace("\"", "")

        versionCode = ReleaseNote.versionCode
        versionName = ReleaseNote.versionName
    }
}

// experimental
composeCompiler {
    enableStrongSkippingMode = true
    includeSourceInformation = true
    stabilityConfigurationFile = project.projectDir.resolve("stability_config.conf")
}

task("testClasses")
