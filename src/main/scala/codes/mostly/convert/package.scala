package codes.mostly

import typings.qs.mod.{IStringifyOptions, stringify => qsStringify}
import typings.qs.qsStrings.RFC1738

import scala.util.Try

package object convert {

  def parseRequest(j: JSObj): Try[Request] = Try(j.asInstanceOf[Request])

  def parseValidationInfo(req: Request): Try[ValidationInfo] =
    for {
      body <- extractQueryStringBody(req)
      ts   <- extractTs(req)
      sig = req.headers.`x-slack-signature`
    } yield ValidationInfo(body, ts, sig)

  private def extractTs(req: Request): Try[Long] = Try(req.headers.`x-slack-request-timestamp`.toLong)

  private def extractQueryStringBody(req: Request): Try[String] =
    Try(qsStringify(req.body, IStringifyOptions(format = RFC1738)))

}
