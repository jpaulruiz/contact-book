file:///D:/Practice/ScalaXVue/Contact-book-2/Contact-book-2/contact-book-app-backend/app/pekko/ContactActor.scala
package pekko

import org.apache.pekko.actor._
import play.api.libs.json.JsValue
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object ContactActor:
    def props(out: ActorRef, manager: ActorRef) = Props(new ContactActor(out, manager))

    case

class ContactActor(out: ActorRef, manager: ActorRef) extends Actor:
    import ContactActor._
    def receive: Actor.Receive = 
        case js: JsValue => 
            manager ! js
        case futureJs: Future[JsValue] =>
            futureJs.map { js => out ! js }
#### Short summary: 

Stack overflow in ContactActor.scala