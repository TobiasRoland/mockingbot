package codes.mostly

import scala.util.Random

class Snarkifier(nextBool: () => Boolean) {

  def snark(text: String): String =
    text
      .zip(Stream.continually(nextBool()))
      .map {
        case (c: Char, true)  => c.toUpper
        case (c: Char, false) => c.toLower
      }
      .mkString("")
}

object Snarkifier {

  private val rng = new Random()

  def apply(generateBoolean: () => Boolean = rng.nextBoolean): Snarkifier = new Snarkifier(generateBoolean)

}
