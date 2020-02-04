package codes.mostly.validate

import codes.mostly.{Milliseconds, Result}

import scala.math.abs
import scala.scalajs.js.Date

class TimestampValidator(time: () => Long = () => new Date().getTime().toLong) {

  def validateTimestamp(ts: Milliseconds, maxDiff: Milliseconds): Result[Unit] = {
    val curr = time()
    val diff = abs(curr - ts)
    Either.cond(
      test = diff <= maxDiff,
      right = (),
      left = new Error(s"Timestamp [$ts] is outside of allowed $maxDiff millisecond difference. Current time: $curr"),
    )
  }
}
