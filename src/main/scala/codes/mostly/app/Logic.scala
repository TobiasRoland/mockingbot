package codes.mostly.app

import codes.mostly._
import codes.mostly.parse.ConfigParser
import codes.mostly.validate.{SignatureValidator, TimestampValidator}

import scala.util.Try

class Logic(
  configParser: ConfigParser,
  tsValidator: TimestampValidator,
  requestParser: JSObj => Try[Request],
  validationParser: Request => Try[ValidationInfo],
  sarcastifier: Snarkifier,
  sigValidator: SignatureValidator,
) {

  def execute(jsRequest: JSObj): Result[String] =
    for {
      config <- configParser.parse()
      req    <- requestParser(jsRequest).toEither
      info   <- validationParser(req).toEither
      _      <- tsValidator.validateTimestamp(info.timeStamp, config.tsMaxDiff)
      _      <- sigValidator.validateSignature(config.slackSecret, info)
      sarcasm = sarcastifier.snark(req.body.text)
    } yield sarcasm

}
