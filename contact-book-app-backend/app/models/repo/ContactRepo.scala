package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain.Contact
import scala.concurrent._

@Singleton
class ContactRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    (using ec: ExecutionContext)
    () extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class ContactTable(tag:Tag) extends Table[Contact](tag,"contact") {
        def id = column[Long]("id", O.PrimaryKey)
        def firstName = column[String]("firstName")
        def middleName = column[Option[String]]("middleName")
        def lastName = column[String]("lastName")
        def phoneNumber = column[String]("phoneNumber")
        def email = column[Option[String]]("email")
        def group = column[Option[String]]("group")

        def * = (id, firstName, middleName, lastName, phoneNumber, email, group).mapTo[Contact]
    }

    val query = TableQuery[ContactTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: Contact) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(username: String) = db.run(
        query.filter(u => u.firstName === username).result.headOption
    )

    def update(id: Long, param: Contact) = db.run(
        query.filter(_.id === id).delete andThen (query += param.copy(id = id)).map(_ > 0)
    )

    def delete(id: Long) = db.run(
        query.filter(_.id === id).delete.map(_ > 0)
    )
}