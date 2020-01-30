package codes.mostly.validation

import codes.mostly.validation.TimestampValidator.CurrentSeconds

import scala.math.abs
import scala.scalajs.js.Date



class TimestampValidator(now: CurrentSeconds = TimestampValidator.defaultNow) {
  def validateTimestamp(ts: Number): Either[String, Unit] = {
    val curr = now()
    abs(curr - ts.longValue()) match {
      case diff if diff <= 60 * 5 => Right()
      case _ =>
        Left(
          s"Timestamp [$ts] is outside of allowed 5 minute difference. Current time: $curr}",
        )
    }
  }
}

object TimestampValidator {

  def apply(): TimestampValidator = new TimestampValidator()

  type CurrentSeconds = () => Long

  val defaultNow: CurrentSeconds = () => new Date().getTime().toLong / 1000
}


