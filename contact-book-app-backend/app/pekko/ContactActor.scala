package pekko

import org.apache.pekko.actor._
import play.api.libs.json.JsValue
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object ContactActor:
    def props(out: ActorRef, manager: ActorRef) = Props(new ContactActor(out, manager))

    case class Message(js: JsValue)

class ContactActor(out: ActorRef, manager: ActorRef) extends Actor:
    import ContactActor._
    def receive: Actor.Receive = 
        case js: JsValue => 
            manager ! js
        case Message(js) =>
            out ! js