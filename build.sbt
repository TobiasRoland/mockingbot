name := """mockingbot"""
scalaVersion := "2.12.10"

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)
scalaJSUseMainModuleInitializer := false

libraryDependencies += "org.scala-js" %%% "scalajs-java-time" % "0.2.6"
libraryDependencies += "io.scalajs.npm" %%% "express" % "4.14.1-4"
libraryDependencies += ScalablyTyped.Q.qs
libraryDependencies += ScalablyTyped.S.`safe-json-stringify`
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.0" % "test"

npmDependencies in Compile += "safe-json-stringify" -> "1.1"
npmDependencies in Compile += "qs" -> "6.9"