
ThisBuild / organization := "mipt"
ThisBuild / version      := "1.0.0"


lazy val root = (project in file("."))
    .enablePlugins(SbtPlugin)
    .settings(
	name := "counting-plugin"
    )
