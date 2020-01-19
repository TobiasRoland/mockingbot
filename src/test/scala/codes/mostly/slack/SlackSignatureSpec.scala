package codes.mostly.slack

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SlackSignatureSpec extends AnyFlatSpec with Matchers {

  "Signature validation" should "successfully validate a valid signature" in {

    
    val slackSigningSecret = "MY_SLACK_SIGNING_SECRET"
    val sig = RequestSignature(
      rawBody =
        "token=xyzz0WbapA4vBCDEFasx0q6G&team_id=T1DC2JH3J&team_domain=testteamnow&channel_id=G8PSS9T3V&channel_name=foobar&user_id=U2CERLKJA&user_name=roadrunner&command=%2Fwebhook-collect&text=&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT1DC2JH3J%2F397700885554%2F96rGlfmibIGlgcZRskXaIFfN&trigger_id=398738663015.47445629121.803a0bc887a14d10d2c447fce8b6703c",
      timeStamp = "1531420618",
      signature =
        "v0=a2114d57b48eac39b9ad189dd8316235a7b4a8d21a10bd27519666489c69b503"
    )

    Slack.validate(slackSigningSecret, sig) shouldBe true
  }
}
