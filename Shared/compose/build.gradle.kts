plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    // id("CompileWasm")
    id("FeatureCoroutine")
    id("FeaturePane")
}

android {
    namespace = "shared.compose"
}

task("testClasses")
