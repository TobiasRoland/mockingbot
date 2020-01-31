package codes

import scala.scalajs.js

/**
  * The package object contains the core models the rest of the app works with.
  */
package object mostly {

  type JSObj       = js.Object
  type Secret      = String
  type Unencrypted = String
  type Encrypted   = String
  type Encrypt     = (Secret, Unencrypted) => Encrypted

  type Result[A] = Either[Throwable, A]

  case class ValidationInfo(body: String, timeStamp: Long, signature: String)

  @js.native
  sealed trait Request extends JSObj {
    val body: SlashCommandBody = js.native
    val headers: SlackHeaders  = js.native
  }

  @js.native
  sealed trait SlashCommandBody extends JSObj {
    val token: String        = js.native
    val team_id: String      = js.native
    val team_domain: String  = js.native
    val channel_id: String   = js.native
    val channel_name: String = js.native
    val user_id: String      = js.native
    val command: String      = js.native
    val text: String         = js.native
    val response_url: String = js.native
    val trigger_id: String   = js.native
  }

  @js.native
  sealed trait SlackHeaders extends JSObj {
    val `x-slack-request-timestamp`: String = js.native
    val `x-slack-signature`: String         = js.native
  }

}
