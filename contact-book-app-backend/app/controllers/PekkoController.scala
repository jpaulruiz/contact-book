package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import models.repos.TemplateRepo
import scala.concurrent.ExecutionContext.Implicits.global

// pekko imports
import pekko._
import pekko.ManagerActor._
import pekko.ContactActor._
import org.apache.pekko.actor._
import org.apache.pekko.stream.Materializer
import org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout
import concurrent.duration.DurationInt
import play.api.libs.streams.ActorFlow
import play.api.libs.json.JsValue


@Singleton
class PekkoController @Inject()(
  @Named("manager-actor") manager: ActorRef,
  val repo: TemplateRepo,
  val controllerComponents: ControllerComponents)
  (implicit val system: ActorSystem,
  val materializer: Materializer) extends BaseController {

    // val helloActor = system.actorOf(PingActor.props(), "hello-actor")
    // implicit val timeout: Timeout = 5.seconds

    def index() = Action.async { implicit request =>
        Future {
            Ok("Hello, world!")
        }
    }

    // def sayHello(name: String) = Action.async {
    //     (helloActor ? PingActor.SayHello(name)).mapTo[String].map { message => Ok(message) }
    // }

    def socket(): WebSocket =
        WebSocket.accept[JsValue, JsValue] { request =>
            ActorFlow.actorRef (out => 
                ContactActor.props(out, manager)
            )
        }
}