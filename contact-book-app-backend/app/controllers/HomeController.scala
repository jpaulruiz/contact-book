package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import models.repos.TemplateRepo
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(
  val repo: TemplateRepo,
  val controllerComponents: ControllerComponents,
) extends BaseController {
  def index() = Action.async { implicit request =>
    Future {
      Ok("Hello, world!")
    }
  }
}