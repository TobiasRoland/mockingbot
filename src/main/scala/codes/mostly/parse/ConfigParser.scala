package codes.mostly.parse

import codes.mostly.{Config, Result}

import scala.util.Try

class ConfigParser(readVariable: String => Option[String]) {
  def parse(): Either[Throwable, Config] = {
    def read(v: String): Result[String] =
      readVariable(v).toRight(new Error(s"Environment variable $v is not set"))
    for {
      secret         <- read("SLACK_SECRET")
      timeDiff       <- read("MAX_TS_DIFF_SECONDS")
      timeDiffMillis <- Try(timeDiff.toLong).map(_ * 1000).toEither
    } yield Config(secret, timeDiffMillis)
  }
}
