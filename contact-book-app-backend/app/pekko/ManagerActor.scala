package pekko

import org.apache.pekko.actor._
import play.api.libs.json.JsValue
import javax.inject._
import models.repo.ContactRepo
import play.api.libs.json._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import pekko.ContactActor.Message

object ManagerActor:
    def props = Props(classOf[ManagerActor])

class ManagerActor @Inject()
    (val contact: ContactRepo) extends Actor:
    import ManagerActor._
    def receive: Actor.Receive = 
        case js: JsValue =>
            val send = sender()
            contact.get.map { list =>
                if (list.size > 0) then 
                    send ! Message(Json.toJson(list))
                else send ! Message(JsObject(Seq("message" -> JsString("No contacts found."))))
            }