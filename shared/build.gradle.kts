plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm()


    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.exposed.core)
            implementation(libs.exposed.jdbc)
            implementation(libs.h2)
            implementation("org.xerial:sqlite-jdbc:3.50.2.0")

            implementation("io.github.vinceglb:filekit-core:0.14.2")
            implementation("io.github.vinceglb:filekit-dialogs:0.14.2")
            implementation("io.github.vinceglb:filekit-dialogs-compose:0.14.2")
            implementation("io.github.vinceglb:filekit-coil:0.14.2")

            implementation("com.drewnoakes:metadata-extractor:2.21.0")
            implementation("net.sf.javamusictag:jid3lib:0.5.4")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
