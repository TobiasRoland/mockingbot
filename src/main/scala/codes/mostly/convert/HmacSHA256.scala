package codes.mostly.convert

import codes.mostly._
import io.scalajs.nodejs.crypto.Crypto.createHmac

object HmacSHA256 extends Encrypt {

  def apply(secret: Secret, text: Unencrypted): Encrypted = {
    val hmac = createHmac("sha256", secret)
    hmac.update(text)
    hmac.digest("hex")
  }

}
