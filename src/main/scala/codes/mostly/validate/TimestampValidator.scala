package codes.mostly.validate

import codes.mostly.Result
import codes.mostly.validate.TimestampValidator.{CurrentTimeMilliseconds, Seconds}

import scala.math.abs
import scala.scalajs.js.Date

class TimestampValidator(readTimestamp: CurrentTimeMilliseconds) {

  def validateTimestamp(ts: Seconds): Result[Unit] = {
    val curr       = readTimestamp()
    val tsInMillis = ts * 1000
    val diff       = abs(curr - tsInMillis)
    Either.cond(
      test = diff <= 60 * 5,
      right = (),
      left = new Error(s"Timestamp [$tsInMillis] is outside of allowed 5 minute difference. Current time: $curr}"),
    )
  }
}

object TimestampValidator {

  type Seconds                 = Long
  type Milliseconds            = Long
  type CurrentTimeMilliseconds = () => Milliseconds

  private val now: CurrentTimeMilliseconds = () => new Date().getTime().toLong

  def apply(readTimestamp: CurrentTimeMilliseconds = now): TimestampValidator = new TimestampValidator(readTimestamp)
}
