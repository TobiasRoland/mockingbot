package codes.mostly

import scala.util.Random

class Sarcastifier(randomBool: () => Boolean) {

  def sarcastify(text: String): String =
    text
      .zip(Stream.continually(randomBool()))
      .map {
        case (c: Char, true)  => c.toUpper
        case (c: Char, false) => c.toLower
      }
      .mkString("")

}

object Sarcastifier {
  private val rng = new Random()

  def apply(nextBool: () => Boolean = rng.nextBoolean): Sarcastifier = new Sarcastifier(nextBool)

}
