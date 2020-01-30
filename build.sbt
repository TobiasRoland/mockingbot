name := """mockingbot"""
scalaVersion := "2.12.10"
//resolvers += Resolver.sonatypeRepo("releases")

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)
scalaJSUseMainModuleInitializer := false

libraryDependencies += "org.scala-js" %%% "scalajs-java-time" % "0.2.6"
libraryDependencies += "io.scalajs.npm" %%% "express" % "4.14.1-4"
libraryDependencies += ScalablyTyped.Q.qs
libraryDependencies += ScalablyTyped.S.`safe-json-stringify`
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.0" % "test"


/* If the library is typed up in `DefinitelyTyped` (has `-dt-` in the version string) you'll also need this.
 * The reason why is that versions strings there are just comments, and frequently wrong. Automatically including
 *  that would break your build.
 */
npmDependencies in Compile += "safe-json-stringify" -> "1.1"
npmDependencies in Compile += "qs" -> "6.9"