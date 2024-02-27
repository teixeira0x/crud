import com.ncorti.ktfmt.gradle.tasks.*

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.0.0" apply false
  id("com.android.library") version "8.0.0" apply false
  id("org.jetbrains.kotlin.android") version "1.8.20" apply false
  id("com.google.dagger.hilt.android") version "2.50" apply false
  id("com.ncorti.ktfmt.gradle") version "0.17.0"
}

ktfmt {
  googleStyle()
  maxWidth.set(80)
}

tasks.register<KtfmtFormatTask>("ktfmtPrecommit") {
  source = project.fileTree(rootDir)
  include("**/*.kt")
}

tasks.register<Delete>("clean") { delete(rootProject.buildDir) }
