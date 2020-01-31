package codes.mostly.validate

import org.scalatest.matchers.should.Matchers
import org.scalatest.FlatSpec
class TimestampValidatorTest extends FlatSpec with Matchers {

  val fixedTimestamp = 1234000L

  val sut = TimestampValidator(() => fixedTimestamp)


}
