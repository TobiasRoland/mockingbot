enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)
resolvers += Resolver.sonatypeRepo("releases")
scalaVersion := "2.12.10"
scalaJSUseMainModuleInitializer := false
scalaJSModuleKind := ModuleKind.CommonJSModule

libraryDependencies ++= Seq(
  "io.scalajs.npm" %%% "express" % "4.14.1-4",
  "org.scala-js" %%% "scalajs-java-time" % "0.2.6",
  "org.scalatest" %%% "scalatest" % "3.1.0" % "test"
)

name := """mockingbot"""

npmDependencies in Compile += "crypto-js" -> "3.1.9-1"
