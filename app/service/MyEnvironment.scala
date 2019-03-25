package service

import javax.inject.{Inject, Singleton}
import akka.actor.ActorSystem
import models.User
import play.api.cache.AsyncCacheApi
import play.api.{Configuration, Environment}
import play.api.i18n.MessagesApi
import play.api.libs.mailer.MailerClient
import play.api.libs.ws.WSClient
import play.api.mvc.PlayBodyParsers
import securesocial.core.RuntimeEnvironment
import securesocial.core.services.UserService

import scala.concurrent.ExecutionContext

@Singleton
class MyEnvironment @Inject() (
                                override val configuration: Configuration,
                                override val messagesApi: MessagesApi,
                                val environment: Environment,
                                val wsClient: WSClient,
                                val cacheApi: AsyncCacheApi,
                                val mailerClient: MailerClient,
                                override val executionContext: ExecutionContext,
                                val parsers: PlayBodyParsers,
                                val actorSystem: ActorSystem) extends RuntimeEnvironment.Default {
  type U = User
  override val userService: UserService[U] = new InMemoryUserService()
}
