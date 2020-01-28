package codes.mostly.slack

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

case class RequestSignature(
  body: String,
  timeStamp: Int,
  signature: String,
)

/**
  * Slack has a fun way of validating requests:
  */
object SlackRequestValidator {

  def validate(key: String, rs: RequestSignature): Either[String, RequestSignature] =
    for {
      _ <- validateSignature(key, rs)
      // _ <- validateTimestamp(rs) // is replays a big risk? Eh.... /shrug
    } yield rs

  private def validateSignature(key: String, rs: RequestSignature): Either[String, RequestSignature] = {
    val (version, hash) = rs.signature.span(_ != '=') match { case (v, h) => (v, h.drop(1)) }
    val baseString      = s"$version:${rs.timeStamp}:${rs.body}"
    val digest          = Crypto.hmacSha256HexDigest(key, baseString)
    val sig             = s"$version=$digest"
    if (rs.signature != sig)
      Left(s"Signature calculated as: [$sig], which did not match the one claimed: [${rs.signature}]")
    else Right(rs)
  }

  // private def validateTimestamp(rs: RequestSignature): Either[String,RequestSignature] = {
  //   val currentTimestamp = new scala.scalajs.js.Date().getTime().toInt
  //   val diff = Math.abs(currentTimestamp - rs.timeStamp)
  //   if (diff > 60 * 5) Left(s"Timestamp is older than is allowed. Current time: $currentTimestamp, timestamp of request: ${rs.timeStamp}")
  //   else Right(rs)
  // }

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
    def update(vtb: String): Unit          = js.native
    def digest(digestType: String): String = js.native
  }

}
