scalaJSModuleKind := ModuleKind.CommonJSModule
scalaJSUseMainModuleInitializer := false
enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
resolvers += Resolver.sonatypeRepo("releases")
scalaVersion := "2.12.10"


libraryDependencies ++= Seq(
  "io.scalajs.npm" %%% "express" % "0.4.2",
  "org.scala-js" %%% "scalajs-java-time" % "0.2.6",
  "org.scalatest" %%% "scalatest" % "3.1.0" % "test",
//  "org.webjars.bowergithub.brix" % "crypto-js" % "3.1.9-1"
)

name := """mockingbot"""

npmDependencies in Compile += "crypto-js" -> "3.1.9-1"
