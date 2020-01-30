package codes.mostly.gcp

import codes.mostly.Sarcastifier
import codes.mostly.slack._
import codes.mostly.validation.{SignatureValidator, TimestampValidator}
import io.scalajs.npm.express.Response
import typings.qs.mod.{IStringifyOptions, stringify => qsStringify}
import typings.qs.qsStrings.RFC1738

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js.{Function2 => JSFunction2}
import scala.util.Try

object EntryPoint {

  val slackSecret = ""
  val headerTimestamp = "x-slack-request-timestamp"
  val headerSlackSignature = "x-slack-signature"

  val sarcastify = Sarcastifier()
  val validator = SlackRequestValidator()
  val tsValidator = TimestampValidator()
  val sigValidator = SignatureValidator(slackSecret)


  @JSExportTopLevel("CloudFunction")
  val CloudFunction: JSFunction2[Request, Response, Unit] = (req, res) ⇒ {

    val validated = for {
      info <- parseValidationInfo(req)
      _ <- tsValidator.validateTimestamp(info.timeStamp)
      _ <- sigValidator.validateSignature(info)
      sarcasm = sarcastify.sarcastify(req.body.text)
    } yield sarcasm

    validated match {
      case Right(sarcasm) => res.status(200).send(sarcasm)
      case err@Left(_) => res.status(400).send(err.toString)
    }
  }

  private def parseValidationInfo(req: Request) = Try {
    ValidationInfo(
      qsStringify(req.body, IStringifyOptions(format = RFC1738)),
      req.headers.`x-slack-request-timestamp`.toDouble,
      req.headers.`x-slack-signature`,
    )
  }.toEither

  @js.native
  trait Request extends js.Object {
    val body: SlashCommandBody = js.native
    val headers: SlackHeaders = js.native
  }

  @js.native
  trait SlashCommandBody extends js.Object {
    val token: String = js.native
    val team_id: String = js.native
    val team_domain: String = js.native
    val channel_id: String = js.native
    val channel_name: String = js.native
    val user_id: String = js.native
    val command: String = js.native
    val text: String = js.native
    val response_url: String = js.native
    val trigger_id: String = js.native
  }

  @js.native
  trait SlackHeaders extends js.Object {
    val `x-slack-request-timestamp`: String = js.native
    val `x-slack-signature`: String = js.native
  }

}
