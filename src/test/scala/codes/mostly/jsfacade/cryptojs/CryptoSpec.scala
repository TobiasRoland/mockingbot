package codes.mostly.jsfacade.cryptojs

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class CryptoSpec extends AnyFlatSpec with Matchers {

  // Replicating the https://github.com/brix/crypto-js/blob/develop/test/hmac-sha256-test.js
  "Hmac256" should "successfully produce the hmac256 for the key + string" in {

    Crypto.hmacSHA256("Jefe", "what do ya want for nothing") should equal("5bdcc146bf60754e6a042426089575c75a003f089d2739839dec58b964ec3843")
  }
}
