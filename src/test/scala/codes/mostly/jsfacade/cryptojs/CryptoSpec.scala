package codes.mostly.jsfacade.cryptojs

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import Crypto._

class CryptoSpec extends AnyFlatSpec with Matchers {

  "HmacSHA256's toString()" should "successfully produce the hmacSHA256 for the key + string" in {
    // Test case from https://github.com/brix/crypto-js/blob/develop/test/hmac-sha256-test.js
    HmacSHA256("what do ya want for nothing?", "Jefe").toString() should equal(
      "5bdcc146bf60754e6a042426089575c75a003f089d2739839dec58b964ec3843"
    )
  }

  it should "successfully produce HmacSHA256 hexdigest when provided with a Hex encoder" in {
    // Test case from https://stackoverflow.com/a/29433000
    HmacSHA256("test", "secret").toString(Hex) should equal(
      "0329a06b62cd16b33eb6792be8c60b158d89a2ee3a876fce9a881ebb488c0914"
    )
  }
}
