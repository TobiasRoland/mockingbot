package codes.mostly.validate

import codes.mostly._
import codes.mostly.convert.HmacSHA256

/**
  * Validator for slack signatures conforming to the guidelines laid out in:
  * https://api.slack.com/docs/verifying-requests-from-slack
  */
class SignatureValidator(slackSecret: Secret, encrypt: Encrypt) {

  def validateSignature(rs: ValidationInfo): Result[Unit] = {
    val version                 = rs.signature.takeWhile(_ != '=') //TODO Consider adding refined so this is 100% definitely "version=hash" so this doesn't have the ability to fail silently here
    val baseString: Unencrypted = s"$version:${rs.timeStamp}:${rs.body}"
    val digest: Encrypted       = encrypt(slackSecret, baseString)
    val calculatedSignature     = s"$version=$digest"
    Either.cond(
      test = calculatedSignature == rs.signature,
      right = (),
      left = new Error(s"Signature calculated as: [${rs.signature}], which did not match the one claimed in: [$rs]"),
    )
  }
}

object SignatureValidator {
  def apply(slackSecret: Secret, encrypt: Encrypt = HmacSHA256): SignatureValidator =
    new SignatureValidator(slackSecret, encrypt)
}
