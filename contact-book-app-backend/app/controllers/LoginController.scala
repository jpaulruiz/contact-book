package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import scala.concurrent._
import java.util.UUID
import models.domain._
import models.repo._
import models.security._
import play.api.libs.json._
import org.checkerframework.checker.units.qual.t

@Singleton
class LoginController @Inject()(val controllerComponents: ControllerComponents,
  val user: UserRepo,
  val Auth: Authenticator)
  (implicit val ec: ExecutionContext) extends BaseController {

    val logonForm = Form(
      tuple(
        "username" -> nonEmptyText,
        "password" -> nonEmptyText
      )
    )

    // def index = loginAuth { (request: UserRequest[AnyContent]) =>
    //     // Ok(JsObject(Seq("message" -> JsString("Logon successfully."))))
    //     Ok(JsObject(Seq("message" -> JsString("Logon successfully."),
    //         "auth-token" -> JsString(request.user.get.auth.get)))).withSession("authToken" -> request.user.get.auth.get)
    // }
    def index = Action.async { (request: Request[AnyContent]) =>
        logonForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                Future.successful(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            u => {
                user.get(u(0)).map {
                  case Some(user) => 
                    if user.password == u(1) then
                      val authToken = UUID.randomUUID().toString
                      Ok(JsObject(Seq("message" -> JsString("Logon successfully."),
                          "authToken" -> JsString(authToken)))).withSession("id" -> authToken)
                    else  Ok(JsObject(Seq("message" -> JsString("Invalid username or password."))))
                  case None =>
                    Ok(JsObject(Seq("message" -> JsString("User does not exist."))))
                }
            }
        )
    }

    def logout = Action { (request: Request[AnyContent]) =>
       Results.Ok.withNewSession
    }

    def isAuthenticated = Auth { (request: UserRequest[AnyContent]) =>
      Results.Ok
    }
}