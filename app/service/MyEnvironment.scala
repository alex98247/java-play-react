package service

import javax.inject.{Inject, Singleton}
import akka.actor.ActorSystem
import models.User
import play.api.{Configuration, Environment}
import play.api.cache.AsyncCacheApi
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
                                override val environment: Environment,
                                override val wsClient: WSClient,
                                override val cacheApi: AsyncCacheApi,
                                override val mailerClient: MailerClient,
                                override val executionContext: ExecutionContext,
                                override val parsers: PlayBodyParsers,
                                override val actorSystem: ActorSystem) extends RuntimeEnvironment.Default {
  type U = User
  override val userService: UserService[U] = new InMemoryUserService()
}
