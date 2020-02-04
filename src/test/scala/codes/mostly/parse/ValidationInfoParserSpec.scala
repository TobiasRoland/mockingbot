package codes.mostly.parse

import codes.mostly.{Request, ValidationInfo}
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js
import scala.util.Success

class ValidationInfoParserSpec extends org.scalatest.FlatSpec with Matchers {

  val requestJSON: String =
    """{
  "headers": {
    "host": "us-central1-iron-bedrock-264011.cloudfunctions.net",
    "user-agent": "Slackbot 1.0 (+https://api.slack.com/robots)",
    "transfer-encoding": "chunked",
    "accept": "application/json,/",
    "accept-encoding": "gzip,deflate",
    "content-type": "application/x-www-form-urlencoded",
    "forwarded": "for=\"34.203.246.211\";proto=https",
    "function-execution-id": "a8epkceruxzq",
    "x-appengine-city": "ashburn",
    "x-appengine-citylatlong": "39.043757,-77.487442",
    "x-appengine-country": "US",
    "x-appengine-default-version-hostname": "db1564e00c689196c-tp.appspot.com",
    "x-appengine-https": "on",
    "x-appengine-region": "va",
    "x-appengine-request-log-id": "5e30e9a500ff0335cc31a00a0d0001737e64623135363465303063363839313936632d7470000164346431323930353139363736663239626166313361376266313861323562663a3138000100",
    "x-appengine-user-ip": "34.203.246.211",
    "x-cloud-trace-context": "f3a5debb0e96c3d7799e305b679f4f70/2904283850727421687;o=1",
    "x-forwarded-for": "34.203.246.211",
    "x-forwarded-proto": "https",
    "x-slack-request-timestamp": "1580263845",
    "x-slack-signature": "v0=ceb9972bef599feebe6285fb13e274df2b8ea5e72d4aad0ad648d4f152b78467",
    "connection": "close"
  },
  "method": "POST",
  "body": {
    "token": "ipITvfUc46RGfhnFoGYl2xjc",
    "team_id": "T3JDZTS4V",
    "team_domain": "e2why",
    "channel_id": "CS123PGE6",
    "channel_name": "tobiastesting",
    "user_id": "U3HMGH3AL",
    "user_name": "roland",
    "command": "/mock",
    "text": "here is some text",
    "response_url": "https://hooks.slack.com/commands/T3JDZTS4V/916342297345/khlBkFKi9mVBCtL8R3QUofFi",
    "trigger_id": "926059080564.120475944165.0da9161c232cc1a4be46eb3bfaf8dbb5"
  }
}
""".stripMargin

  "Validation info" should "parse the validation info from the Request" in {
    val jsObj: js.Dynamic = js.JSON.parse(requestJSON)
    val req: Request      = jsObj.asInstanceOf[Request] // can't be bothered writing thi
    ValidationInfoParser(req) should equal(
      Success(
        ValidationInfo(
          body =
            "token=ipITvfUc46RGfhnFoGYl2xjc&team_id=T3JDZTS4V&team_domain=e2why&channel_id=CS123PGE6&channel_name=tobiastesting&user_id=U3HMGH3AL&user_name=roland&command=%2Fmock&text=here+is+some+text&response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT3JDZTS4V%2F916342297345%2FkhlBkFKi9mVBCtL8R3QUofFi&trigger_id=926059080564.120475944165.0da9161c232cc1a4be46eb3bfaf8dbb5",
          timeStamp = 1580263845,
          signature = "v0=ceb9972bef599feebe6285fb13e274df2b8ea5e72d4aad0ad648d4f152b78467",
        ),
      ),
    )
  }

}
