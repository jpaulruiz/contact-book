package models.security

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import models.repo.UserRepo
import scala.concurrent._
import play.api.libs.json._
import play.api.libs._
import scala.collection.mutable.ListBuffer

class UserRequest[A](val auth: Option[String], request: Request[A]) extends WrappedRequest[A](request)

@Singleton
class Authenticator @Inject() 
    (bodyParser: BodyParsers.Default,
        list: UserRepo)
    (using ec: ExecutionContext)
    extends ActionBuilder[UserRequest, AnyContent] {
    val logger = Logger(this.getClass)
    def parser: play.api.mvc.BodyParser[play.api.mvc.AnyContent] = bodyParser
    protected def executionContext: scala.concurrent.ExecutionContext = ec

    override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
        if getAuth(request) then
            block(new UserRequest(Some("Authenticated"), request))
        else Future.successful(Results.Unauthorized)
    }

    def getAuth[A](request: Request[A]): Boolean = {
        request.cookies.get("PLAY3").map(_.value) match 
            case Some(value) =>
                request.headers.toMap.get("cookie").getOrElse(List[String]()).map(
                    cookie => {
                        val parts = cookie.split("=")
                        println(s"cookie: ${parts(1)}")
                        println(s"value: $value")
                        if parts(1) == value then
                            true
                        else
                            false
                    }
                )(0)
            case None =>
                println("no cookie")
                false
    }
}