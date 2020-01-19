package codes.mostly.slack

case class RequestSignature(
    rawBody: String,
    timeStamp: String,
    signature: String
)

object Slack {

  // Won't work in scala.js because of javax imports, need to pull in crypto-js instead. 
  
  // val signatureVersion = "v0"

  // def validate(key: String, sig: RequestSignature): Boolean = {
  //   val baseString = signatureVersion + ":" + sig.timeStamp + ":" + sig.rawBody
  //   val hmacSha256Bytes = asHmacSHA256(key, baseString)
  //   val hex = hmacSha256Bytes.map("%02X" format _).mkString
  //   val actualSignature = "v0=" + hex
  //   actualSignature.equals(sig.signature)
  // }

  // import javax.crypto.Mac
  // import javax.crypto.spec.SecretKeySpec

  // private def asHmacSHA256(key: String, baseString: String): Array[Byte] = {
  //   val secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256")
  //   val hmac = Mac.getInstance("HmacSHA256")
  //   hmac.init(secretKeySpec)
  //   hmac.doFinal(baseString.getBytes())
  // }
}
