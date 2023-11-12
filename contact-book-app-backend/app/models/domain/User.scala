package models.domain

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class User(username: String, password: String)

object User:
    val tupled = (apply: (String, String) => User).tupled
    
    given userWrites: Writes[User] = (
        (JsPath \ "username").write[String] and
        (JsPath \ "password").write[String]
    )(l => (l.username, l.password))