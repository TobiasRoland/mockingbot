package codes.mostly.validate

import org.scalatest.FlatSpec
import org.scalatest.matchers.should.Matchers
class TimestampValidatorTest extends FlatSpec with Matchers {

  val fixedTimestamp = 1234000L

  val sut = new TimestampValidator(time = () => fixedTimestamp)

  "TS Validation" should "pass when within range" in { //TODO could make this a property based test
    sut.validateTimestamp(ts = fixedTimestamp - 1, maxDiff = 1) should equal(Right())
    sut.validateTimestamp(ts = fixedTimestamp, maxDiff = 1) should equal(Right())
    sut.validateTimestamp(ts = fixedTimestamp + 1, maxDiff = 1) should equal(Right())
  }

  it should "fail when outside range" in { //TODO could make this a property based test
    sut.validateTimestamp(ts = fixedTimestamp + 2, maxDiff = 1).isLeft should equal(true)
    sut.validateTimestamp(ts = fixedTimestamp - 2, maxDiff = 1).isLeft should equal(true)
  }

}
