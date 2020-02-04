package codes.mostly.validate

import codes.mostly._
import codes.mostly.parse.HmacSHA256

/**
  * Validator for slack signatures conforming to the guidelines laid out in:
  * https://api.slack.com/docs/verifying-requests-from-slack
  */
class SignatureValidator(encrypt: Encrypt) {

  def validateSignature(secret: Secret, v: ValidationInfo): Result[Unit] = {
    val version                 = v.signature.takeWhile(_ != '=') //TODO Consider adding refined so this is 100% definitely "version=hash" so this doesn't have the ability to fail silently here
    val baseString: Unencrypted = s"$version:${v.timeStamp}:${v.body}"
    val digest: Encrypted       = encrypt(secret, baseString)
    val calculatedSignature     = s"$version=$digest"
    Either.cond(
      test = calculatedSignature == v.signature,
      right = (),
      left = new Error(s"Signature calculated as: [${v.signature}], which did not match the one claimed in: [$v]"),
    )
  }
}
