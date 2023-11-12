package models.domain

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Contact(
    id: Long = 1L,
    firstName: String, 
    middleName: Option[String], 
    lastName: String, 
    phoneNumber: String,
    email: Option[String],
    group: Option[String])

object Contact:

    val tupled = (apply: (Long, String, Option[String], String, String, Option[String], Option[String]) => Contact).tupled

    def unapply(contact: Contact): Option[(Long, String, Option[String], String, String, Option[String], Option[String])] =
        Some((contact.id, contact.firstName, contact.middleName, contact.lastName, contact.phoneNumber, contact.email, contact.group))

    given contactWrites: Writes[Contact] = (
        (JsPath \ "id").write[Long] and
        (JsPath \ "firstName").write[String] and
        (JsPath \ "middleName").writeNullable[String] and
        (JsPath \ "lastName").write[String] and
        (JsPath \ "phoneNumber").write[String] and
        (JsPath \ "email").writeNullable[String] and
        (JsPath \ "group").writeNullable[String]
    )(l => (l.id, l.firstName, l.middleName, l.lastName, l.phoneNumber, l.email, l.group))