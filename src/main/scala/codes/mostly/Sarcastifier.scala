package codes.mostly

import codes.mostly.Sarcastifier.NextBool

import scala.collection.immutable.Stream.continually
import scala.util.Random

class Sarcastifier(booleanSource: NextBool = Sarcastifier.nextBool) {

  def sarcastify(text: String): String =
    text
      .zip(continually(booleanSource()))
      .map {
        case (c: Char, true) => c.toUpper
        case (c: Char, false) => c.toLower
      }
      .mkString("")

}

object Sarcastifier {

  def apply(): Sarcastifier = new Sarcastifier()

  type NextBool = () => Boolean
  private val DefaultRand = new Random()

  private val nextBool: NextBool = DefaultRand.nextBoolean
}
