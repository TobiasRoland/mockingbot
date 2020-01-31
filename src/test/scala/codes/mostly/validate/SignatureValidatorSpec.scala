package codes.mostly.validate

import codes.mostly.ValidationInfo
import codes.mostly.convert.HmacSHA256
import org.scalatest.matchers.should.Matchers

/**
  * Test data taken from slack's documentation for validation Jan 20, 2020:
  * https://api.slack.com/docs/verifying-requests-from-slack
  */
class SignatureValidatorSpec extends org.scalatest.FlatSpec with Matchers {

  // Using the actual HmacSHA256 since it makes more sense to test this together IMO.
  val sut: SignatureValidator = SignatureValidator(
    slackSecret = "8f742231b10e8888abcd99yyyzzz85a5",
    encrypt = HmacSHA256
  )

  private val correctSignature = "v0=a2114d57b48eac39b9ad189dd8316235a7b4a8d21a10bd27519666489c69b503"
  private val correctTimestamp = 1531420618
  private val correctBody = "token=xyzz0WbapA4vBCDEFasx0q6G&team_id=T1DC2JH3J&team_domain=testteamnow&channel_id=G8PSS9T3V&channel_name=foobar&user_id=U2CERLKJA&user_name=roadrunner&command=%2Fwebhook-collect&text=&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT1DC2JH3J%2F397700885554%2F96rGlfmibIGlgcZRskXaIFfN&trigger_id=398738663015.47445629121.803a0bc887a14d10d2c447fce8b6703c"

  "Signature validation" should "validate when all is good" in {
    sut.validateSignature(ValidationInfo(correctBody, correctTimestamp, correctSignature)) should equal(Right())
  }

  it should "invalidate when body is incorrect" in {
    sut.validateSignature(ValidationInfo(correctBody + "a", correctTimestamp, correctSignature)).isLeft shouldBe true
  }

  it should "invalidate when timestamp is incorrect" in {
    sut.validateSignature(ValidationInfo(correctBody, correctTimestamp, correctSignature + "a")).isLeft shouldBe true
  }

}
