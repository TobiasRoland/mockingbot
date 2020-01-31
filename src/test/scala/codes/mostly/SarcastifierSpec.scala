package codes.mostly

import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

class SarcastifierSpec extends org.scalatest.FlatSpec with Matchers {

  private val input = "You should build a Slackbot"

  "Sarcastifying" should "lowercase all when boolean source is always false" in {
    val alwaysFalse = () => false
    val actual = Sarcastifier(nextBool = alwaysFalse).sarcastify(input)
    actual should equal("you should build a slackbot")
  }

  it should "uppercase all when boolean source is always true" in {
    val alwaysTrue = () => true
    val actual = Sarcastifier(nextBool = alwaysTrue).sarcastify(input)
    actual should equal("YOU SHOULD BUILD A SLACKBOT")
  }

  it should "alternate when boolean flips" in {
    val infiniteFlipFlop = Stream.iterate(false)(b => !b)
    val finiteFlipFlop = mutable.Queue(infiniteFlipFlop.take(27): _*)
    val flipFLop = () => finiteFlipFlop.dequeue()
    val actual = Sarcastifier(nextBool = flipFLop).sarcastify(input)
    actual should equal("yOu sHoUlD BuIlD A SlAcKbOt")
  }

}
