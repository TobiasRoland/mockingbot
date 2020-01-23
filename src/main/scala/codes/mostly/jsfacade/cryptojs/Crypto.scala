package codes.mostly.jsfacade.cryptojs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * Wraps crypto-js/hmac-sha256 to make it less... javascript-y
 */
@js.native
@JSImport("crypto-js", JSImport.Namespace) 
object Crypto extends js.Object {
    def hmacSHA256(key: String, text: String): String = js.native
}
