package codes.mostly.app

import codes.mostly.convert.HmacSHA256
import codes.mostly.validate.{SignatureValidator, TimestampValidator}
import codes.mostly.{JSObj, Sarcastifier, convert}
import io.scalajs.npm.express.Response

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Function2 => JSFunction2}

object EntryPoint {

  val logic = new AppLogic(
    convert.parseRequest,
    convert.parseValidationInfo,
    Sarcastifier(),
    SignatureValidator("", HmacSHA256.apply),
    TimestampValidator(),
  )

  @JSExportTopLevel("CloudFunction")
  val CloudFunction: JSFunction2[JSObj, Response, Unit] = (req, res) ⇒ {
    logic.execute(req) match {
      case Right(sarcasticResponse) => res.status(200).send(sarcasticResponse)
      case Left(err)                => res.status(400).send(err.toString)
    }

  }

}
