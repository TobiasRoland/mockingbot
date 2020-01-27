package codes.mostly.jsfacade.cryptojs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
sealed trait Enc extends js.Object

@js.native
@JSImport("crypto-js", "enc.Hex")
object Hex extends Enc {
 def stringify(wordArray: WordArray): String = js.native
}


@js.native
trait WordArray extends js.Object {
  def toString(enc: Enc): String = js.native
  override def toString(): String = js.native
}

@js.native
@JSImport("crypto-js", JSImport.Namespace)
object Crypto extends js.Object {
  def HmacSHA256(key: String, text: String): WordArray = js.native
}
