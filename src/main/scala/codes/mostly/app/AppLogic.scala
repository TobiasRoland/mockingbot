package codes.mostly.app

import codes.mostly.validate.{SignatureValidator, TimestampValidator}
import codes.mostly._

import scala.util.Try

class AppLogic(
  toReq: JSObj => Try[Request],
  toValidationInfo: Request => Try[ValidationInfo] = convert.parseValidationInfo,
  sarcastifier: Sarcastifier,
  sigValidator: SignatureValidator,
  tsValidator: TimestampValidator,
) {

  def execute(jsRequest: JSObj): Result[String] =
    for {
      req  <- toReq(jsRequest).toEither
      info <- toValidationInfo(req).toEither
      _    <- tsValidator.validateTimestamp(info.timeStamp)
      _    <- sigValidator.validateSignature(info)
      sarcasm = sarcastifier.sarcastify(req.body.text)
    } yield sarcasm

}
