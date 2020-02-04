package codes.mostly.app

import codes.mostly.parse.{ConfigParser, HmacSHA256, ValidationInfoParser}
import codes.mostly.validate.{SignatureValidator, TimestampValidator}
import codes.mostly.{JSObj, Milliseconds, Request, Snarkifier}
import io.scalajs.npm.express.Response

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Date, Function2 => JSFunction2}
import scala.util.{Random, Try}

object App {

  val readTime: () => Milliseconds         = () => new Date().getTime().toLong
  val readEnvVar: String => Option[String] = io.scalajs.nodejs.global.process.env.get
  val rng: Random                          = new Random
  val logic = new Logic(
    configParser = new ConfigParser(readVariable = readEnvVar),
    tsValidator = new TimestampValidator(time = readTime),
    requestParser = jsObj => Try(jsObj.asInstanceOf[Request]),
    validationParser = ValidationInfoParser(_),
    sarcastifier = Snarkifier(generateBoolean = rng.nextBoolean),
    sigValidator = new SignatureValidator(HmacSHA256),
  )

  @JSExportTopLevel("CloudFunction")
  val CloudFunction: JSFunction2[JSObj, Response, Unit] = (req, res) ⇒ {
    logic.execute(req) match {
      case Right(sarcasticResponse) => res.status(200).send(sarcasticResponse)
      case Left(err)                => res.status(200).send("Error!\n\n" + err.toString)
    }
  }

}
