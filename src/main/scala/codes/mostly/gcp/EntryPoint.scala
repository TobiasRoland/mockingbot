package codes.mostly.gcp

import codes.mostly.slack._
import io.scalajs.npm.express.{Request, Response}
import typings.std.console
import typings.std.stdStrings.string

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Function2 => JSFunction2}

object EntryPoint {

  val headerTimestamp = "x-slack-request-timestamp"
  val headerSlackSignature = "x-slack-signature"

  @JSExportTopLevel("HelloWorld")
  val Mock: JSFunction2[Request, Response, Unit] = (req, res) ⇒ {
    val requestSignature = parseRequestSignature(req)
    console.log("" + requestSignature)
    res.status(200).send(requestSignature.toString)
  }


  private def parseRequestSignature(req: Request): Option[RequestSignature] = {
    val headers = req.headers.toMap[String, String]
    val requestSignature: Option[RequestSignature] = for {
      ts <- headers.get(headerTimestamp)
      sig <- headers.get(headerSlackSignature)
      body = req.bodyAs[string].toString
    } yield RequestSignature(body, ts.toInt, sig)
    requestSignature
  }
}
