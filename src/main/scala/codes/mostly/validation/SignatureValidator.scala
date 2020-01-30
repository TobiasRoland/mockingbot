package codes.mostly.validation

import codes.mostly.slack.ValidationInfo

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

class SignatureValidator(slackSecret: String) {

  def validateSignature(rs: ValidationInfo): Either[String, ValidationInfo] = {
    val version = rs.signature.takeWhile(_ != '=')
    val baseString = s"$version:${rs.timeStamp}:${rs.body}"
    val digest = Crypto.hmacSha256HexDigest(slackSecret, baseString)
    s"$version=$digest" match {
      case rs.signature => Right(rs)
      case otherwise => Left(s"Signature calculated as: [$otherwise], which did not match the one claimed in: [$rs]")
    }
  }
}

object SignatureValidator {
  def apply(slackSecret: String): SignatureValidator = new SignatureValidator(slackSecret)
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
