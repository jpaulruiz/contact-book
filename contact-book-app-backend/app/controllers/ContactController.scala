package controllers

import javax.inject._
import play.api.mvc._
import play.api._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent._
import models.domain._
import models.repo._
import models.security._
import play.api.libs.json._
import play.api.data.FormBinding.Implicits.formBinding

@Singleton
class ContactController @Inject()(val controllerComponents: ControllerComponents,
    val user: UserRepo,
    val contact: ContactRepo)
    (implicit val ec: ExecutionContext) extends BaseController {

    val contactCreationForm: Form[Contact] = Form(
        mapping(
            "id" -> ignored(0L),
            "firstName" -> nonEmptyText,
            "middleName" -> optional(text),
            "lastName" -> nonEmptyText,
            "phoneNumber" -> nonEmptyText,
            "email" -> optional(text),
            "group" -> optional(text)
        )(Contact.apply)(Contact.unapply)
    )

    val contactForm: Form[Contact] = Form(
        mapping(
            "id" -> longNumber,
            "firstName" -> nonEmptyText,
            "middleName" -> optional(text),
            "lastName" -> nonEmptyText,
            "phoneNumber" -> nonEmptyText,
            "email" -> optional(text),
            "group" -> optional(text)
        )(Contact.apply)(Contact.unapply)
    )

    def create = Action.async { (request: Request[AnyContent]) =>
        contactCreationForm.bindFromRequest()(request, formBinding).fold(
            formWithErrors => {
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            c => {
                contact.get.map { list =>
                    contact.create(c.copy(id = list.size + 1))
                }
                Future(Ok(JsObject(Seq("message" -> JsString("Contact successfully created.")))))
            }
        )
    }

    def get = Action.async { (request: Request[AnyContent]) =>
        contact.get.map { list =>
            if (list.size > 0) then 
                Ok(Json.toJson(list))
            else Ok(JsObject(Seq("message" -> JsString("No contacts found."))))
        }
    }

    def update = Action.async { (request: Request[AnyContent]) =>
        contactForm.bindFromRequest()(request, formBinding).fold(
            formWithErrors => {
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            c => {
                contact.update(c.id, c).map{
                    case true =>
                        Ok(JsObject(Seq("message" -> JsString("Contact successfully updated."))))
                    case false =>
                        Ok(JsObject(Seq("message" -> JsString("Error updating contact."))))
                }
            }
        )
    }

    val contactDeletionForm = Form(
        tuple(
            "id" -> longNumber,
            "none" -> ignored(text)
        )
    )

    def delete = Action.async { (request: Request[AnyContent]) =>
        contactDeletionForm.bindFromRequest()(request, formBinding).fold(
            formWithErrors => {
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            c => {
                contact.delete(c(0)).map {
                    case true =>
                        Ok(JsObject(Seq("message" -> JsString("Contact successfully deleted."))))
                    case false =>
                        Ok(JsObject(Seq("message" -> JsString("Error deleting contact."))))
                }
            }
        )
    }
}