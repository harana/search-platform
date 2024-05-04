import sbt._
import com.harana.sbt.common._

val searchPlatform = haranaCrossProject("search-platform").in(file("."))
	.settings(
		libraryDependencies ++= Seq(
			"com.harana" %%% "id-jwt" % "1.0.1",
		),
  ).jvmSettings(
		libraryDependencies ++=
			Library.zio2.value :+
			"com.harana" %% "app" % "1.0.0" :+
			"com.harana" %% "modules" % "1.0.1"
	).jsSettings(
		libraryDependencies ++= Seq(
			"com.harana" %%% "web" % "1.0.11-dirty-SNAPSHOT"
		)
	)
val root = haranaRootProject(searchPlatform)