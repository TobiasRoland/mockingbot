package codes.mostly.parse

import codes.mostly.Config
import org.scalatest.matchers.should.Matchers

class ConfigParserSpec extends org.scalatest.FlatSpec with Matchers {

  "Config" should "be parsed correctly when all properties present" in {
    val sut: ConfigParser = new ConfigParser(readVariable = {
      case "SLACK_SECRET"        => Some("secret")
      case "MAX_TS_DIFF_SECONDS" => Some("1234")
      case _                     => None
    })
    sut.parse() should equal(Right(Config("secret", 1234000L)))
  }

  it should "fail when no properties present" in {
    val sut: ConfigParser = new ConfigParser(readVariable = _ => None)
    sut.parse().isLeft should be(true)
  }

  it should "fail when ts diff isn't number" in {
    val sut: ConfigParser = new ConfigParser(readVariable = {
      case "SLACK_SECRET"        => Some("secret")
      case "MAX_TS_DIFF_SECONDS" => Some("I am not a long")
      case _                     => None
    })

    sut.parse().isLeft should be(true)

  }

}
