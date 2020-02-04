package codes.mostly

import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

class SnarkifierSpec extends org.scalatest.FlatSpec with Matchers {

  private val input = "You should build a Slackbot"

  "Snarkifying" should "lowercase all when boolean source is always false" in {
    val alwaysFalse = () => false
    val actual      = Snarkifier(generateBoolean = alwaysFalse).snark(input)
    actual should equal("you should build a slackbot")
  }

  it should "uppercase all when boolean source is always true" in {
    val alwaysTrue = () => true
    val actual     = Snarkifier(generateBoolean = alwaysTrue).snark(input)
    actual should equal("YOU SHOULD BUILD A SLACKBOT")
  }

  it should "alternate when boolean flips" in {
    val infiniteFlipFlop = Stream.iterate(false)(b => !b)
    val finiteFlipFlop   = mutable.Queue(infiniteFlipFlop.take(27): _*)
    val flipFLop         = () => finiteFlipFlop.dequeue()
    val actual           = Snarkifier(generateBoolean = flipFLop).snark(input)
    actual should equal("yOu sHoUlD BuIlD A SlAcKbOt")
  }

}
