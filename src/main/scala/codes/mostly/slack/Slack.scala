package codes.mostly.slack

import codes.mostly.jsfacade.cryptojs.Crypto

case class RequestSignature(
    rawBody: String,
    timeStamp: String,
    signature: String
)

object Slack {

  // val signatureVersion = "v0"

  // def validate(key: String, sig: RequestSignature): Boolean = {
  //   val baseString = signatureVersion + ":" + sig.timeStamp + ":" + sig.rawBody
  //   val hmacSha256Bytes = asHmacSHA256(key, baseString)
  //   val hex = hmacSha256Bytes.map("%02X" format _).mkString
  //   val actualSignature = "v0=" + hex
  //   actualSignature.equals(sig.signature)
  // }

  // private def asHmacSHA256(key: String, baseString: String): Array[Byte] = {
  //   val hmacSha256 = Crypto.HmacSHA256(key, baseString)
  //   val hexed = 
  // }
}
