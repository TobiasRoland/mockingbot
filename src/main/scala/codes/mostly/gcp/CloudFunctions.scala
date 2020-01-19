package codes.mostly.gcp

import io.scalajs.npm.express.{Request, Response}

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Function2 => JSFunction2}

object CloudFunctions {

  /**
    * The function that will can be invoked in GCP Cloud Functions
    */
  @JSExportTopLevel("HelloWorld")
  val Mock: JSFunction2[Request, Response, Unit] = (req, res) ⇒ {
    res.status(200).send("Hello World")
  }

}
