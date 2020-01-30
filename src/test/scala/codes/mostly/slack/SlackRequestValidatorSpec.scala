package codes.mostly.slack

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

// Test data from: https://api.slack.com/docs/verifying-requests-from-slack
class SlackRequestValidatorSpec extends AnyFlatSpec with Matchers {

  "Signature validation" should "validate" in {
    val ts        = 1531420618
    val signature = "v0=a2114d57b48eac39b9ad189dd8316235a7b4a8d21a10bd27519666489c69b503"
    val body =
      "token=xyzz0WbapA4vBCDEFasx0q6G&team_id=T1DC2JH3J&team_domain=testteamnow&channel_id=G8PSS9T3V&channel_name=foobar&user_id=U2CERLKJA&user_name=roadrunner&command=%2Fwebhook-collect&text=&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT1DC2JH3J%2F397700885554%2F96rGlfmibIGlgcZRskXaIFfN&trigger_id=398738663015.47445629121.803a0bc887a14d10d2c447fce8b6703c"
    val rs = ValidationInfo(body, ts, signature)
    SlackRequestValidator.validate("8f742231b10e8888abcd99yyyzzz85a5", rs) should equal(Right(rs))
  }

  "Signature validation" should "invalidate" in {
    val ts        = 1024
    val signature = "v0=a2114d57b48eac39b9ad189dd8316235a7b4a8d21a10bd27519666489c69b503"
    val body =
      "token=xyzz0WbapA4vBCDEFasx0q6G&team_id=T1DC2JH3J&team_domain=testteamnow&channel_id=G8PSS9T3V&channel_name=foobar&user_id=U2CERLKJA&user_name=roadrunner&command=%2Fwebhook-collect&text=&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT1DC2JH3J%2F397700885554%2F96rGlfmibIGlgcZRskXaIFfN&trigger_id=398738663015.47445629121.803a0bc887a14d10d2c447fce8b6703c"
    val rs = ValidationInfo(body, ts, signature)
    SlackRequestValidator.validate("8f742231b10e8888abcd99yyyzzz85a5", rs).isLeft shouldBe true
  }
}
