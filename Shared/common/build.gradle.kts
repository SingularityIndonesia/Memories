plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    /*id("CompileWasm")*/
    id("FeatureCoroutine")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

android {
    namespace = "shared.common"
}

task("testClasses")