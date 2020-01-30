package codes.mostly.gcp

import codes.mostly.gcp.EntryPoint.Request
import io.scalajs.npm.express.Response
import org.scalatest.matchers.should.Matchers
import org.scalatest.FlatSpec

import scala.scalajs.js
import scala.scalajs.js.JSON.parse

class EntryPointTest extends FlatSpec with Matchers {

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
  },
  "rawBody": {
    "type": "Buffer",
    "data": [
      116,111,107,101,110,61,105,112,73,84,118,102,85,99,52,54,82,71,102,104,110,70,111,71,89,108,50,120,106,99,38,116,101,97,109,95,105,100,61,84,51,74,68,90,84,83,52,86,38,116,101,97,109,95,100,111,109,97,105,110,61,101,50,119,104,121,38,99,104,97,110,110,101,108,95,105,100,61,67,83,49,50,51,80,71,69,54,38,99,104,97,110,110,101,108,95,110,97,109,101,61,116,111,98,105,97,115,116,101,115,116,105,110,103,38,117,115,101,114,95,105,100,61,85,51,72,77,71,72,51,65,76,38,117,115,101,114,95,110,97,109,101,61,114,111,108,97,110,100,38,99,111,109,109,97,110,100,61,37,50,70,109,111,99,107,38,116,101,120,116,61,104,101,114,101,43,105,115,43,115,111,109,101,43,116,101,120,116,38,114,101,115,112,111,110,115,101,95,117,114,108,61,104,116,116,112,115,37,51,65,37,50,70,37,50,70,104,111,111,107,115,46,115,108,97,99,107,46,99,111,109,37,50,70,99,111,109,109,97,110,100,115,37,50,70,84,51,74,68,90,84,83,52,86,37,50,70,57,49,54,51,52,50,50,57,55,51,52,53,37,50,70,107,104,108,66,107,70,75,105,57,109,86,66,67,116,76,56,82,51,81,85,111,102,70,105,38,116,114,105,103,103,101,114,95,105,100,61,57,50,54,48,53,57,48,56,48,53,54,52,46,49,50,48,52,55,53,57,52,52,49,54,53,46,48,100,97,57,49,54,49,99,50,51,50,99,99,49,97,52,98,101,52,54,101,98,51,98,102,97,102,56,100,98,98,53
    ]
  }
}
""".stripMargin

  "Entrypoint" should "parse the json and do things right" in {
    val req = parse(requestJSON).asInstanceOf[Request]
    EntryPoint.Mock(req, null)
  }

}
