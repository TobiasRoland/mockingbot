package codes.mostly.gcp

import codes.mostly.slack._
import io.scalajs.nodejs.querystring.QueryString
import io.scalajs.npm.express.{Request => ExpressRequest, Response}
import typings.std.{JSON, console}
import typings.qs.qsRequire
import typings.qs.qsStrings.RFC1738
import typings.qs
import typings.qs.mod.{IStringifyOptions, stringify => qsStringify}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport, ScalaJSDefined}
import scala.scalajs.js.{Function2 => JSFunction2}

object EntryPoint {

  val slackSecret = "" //TODO
  val headerTimestamp = "x-slack-request-timestamp"
  val headerSlackSignature = "x-slack-signature"

  @JSExportTopLevel("HelloWorld")
  val Mock: JSFunction2[ExpressRequest, Response, Unit] = (req, res) ⇒ {
    val request = req.asInstanceOf[Request]
    val signature = parseRequestSignature(request)
//    val validation = SlackRequestValidator.validate(slackSecret, requestSignature)
    res.status(200).send("" + signature.toString)
  }

  private def parseRequestSignature(req: Request): RequestSignature = {
    RequestSignature(
      qsStringify(req.body, IStringifyOptions(format = RFC1738)),
      req.headers.`x-slack-request-timestamp`.toInt,
      req.headers.`x-slack-signature`
    )
  }

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
