package codes.mostly.slack

import codes.mostly.slack.Crypto.hmacSha256HexDigest
import codes.mostly.validation.{SignatureValidator, TimestampValidator}

import scala.scalajs.js
import scala.scalajs.js.Date
import scala.scalajs.js.annotation.JSImport

case class ValidationInfo(body: String, timeStamp: Double, signature: String)

/**
  * Slack has a fun way of validating requests:
  */
class SlackRequestValidator(tsValidator: TimestampValidator = TimestampValidator(),
                            sigValidator: SignatureValidator = SignatureValidator(slackSecret =)) {

  def validate(key: String, rs: ValidationInfo): Either[String, ValidationInfo] =
    for {
      _ <- tsValidator.validateTimestamp(rs.timeStamp)
      _ <- validateSignature(key, rs)
    } yield rs

  /**
    * Validates the signature is correct
    */
//  private def validateSignature(key: String, rs: ValidationInfo): Either[String, ValidationInfo] = {
//    val version = rs.signature.takeWhile(_ != '=')
//    val baseString = s"$version:${rs.timeStamp}:${rs.body}"
//    val digest = hmacSha256HexDigest(key, baseString)
//    s"$version=$digest" match {
//      case rs.signature => Right(rs)
//      case otherwise => Left(s"Signature calculated as: [$otherwise], which did not match the one claimed in: [$rs]")
//    }

//  }

  //  /**
  //    * Protects against replay attacks (outside of a 5 minute window)
  //    */
  //  private def validateTimestamp(rs: ValidationInfo): Either[String, ValidationInfo] = {
  //    val tsMillis  = new Date().getTime()
  //    val tsSeconds = tsMillis / 1000
  //    Math.abs(tsSeconds - rs.timeStamp) match {
  //      case diff if diff <= 60 * 5 => Right(rs)
  //      case _ =>
  //        Left(
  //          s"Timestamp is outside of allowed 5 minute difference. Current time: $tsSeconds, request: ${rs.timeStamp}",
  //        )
  //    }
  //  }

}

object SlackRequestValidator {
  def apply(): SlackRequestValidator = new SlackRequestValidator()
}

/**
  * Wraps node.js' crypto module (well, just the parts we need, thankfully) for easy use, and to hide some ugly string parameters.
  */
private object Crypto {

  def hmacSha256HexDigest(secret: String, text: String): String = {
    val hmacSha256: Hmac = NodeCrypto.createHmac("sha256", secret)
    hmacSha256.update(text)
    hmacSha256.digest("hex")
  }

  @js.native
  @JSImport("crypto", JSImport.Namespace)
  private object NodeCrypto extends js.Object {
    def createHmac(alg: String, key: String): Hmac = js.native
  }

  @js.native
  private trait Hmac extends js.Object {
    def update(vtb: String): Unit = js.native

    def digest(digestType: String): String = js.native
  }

}
