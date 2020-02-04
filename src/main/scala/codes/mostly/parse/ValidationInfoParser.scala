package codes.mostly.parse

import codes.mostly.{Request, ValidationInfo}
import typings.qs.mod.{IStringifyOptions, stringify => qsStringify}
import typings.qs.qsStrings.RFC1738

import scala.util.Try

object ValidationInfoParser {

  def apply(req: Request): Try[ValidationInfo] =
    for {
      body <- Try(qsStringify(req.body, IStringifyOptions(format = RFC1738)))
      ts   <- Try(req.headers.`x-slack-request-timestamp`.toLong)
      sig = req.headers.`x-slack-signature`
    } yield ValidationInfo(body, ts, sig)
}
