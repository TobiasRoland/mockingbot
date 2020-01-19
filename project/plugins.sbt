addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.31")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.14.0")

/**
 * Adding later version (0.15 or 0.16) causes:
 * 
 /mnt/c/Users/Tobias Roland/IdeaProjects/mockingbot/build.sbt:1: 
     error: not found: value scalaJSModuleKind
     scalaJSModuleKind := ModuleKind.CommonJSModule
     [error] Type error in expression


     ... amongst other interesting errors.
*/ 

