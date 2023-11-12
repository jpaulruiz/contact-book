package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain.User


@Singleton
class UserRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    () extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class UserTable(tag: Tag) extends Table[User](tag, "user") {
        def username = column[String]("username", O.PrimaryKey)
        def password = column[String]("password")

        def * = (username, password).mapTo[User]
    }

    val query = TableQuery[UserTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: User) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(username: String) = db.run(
        query.filter(u => u.username === username).result.headOption
    )

    // def update(param: User) = db.run (
    //     query.filter(u => u.username === param.username).map(u => (u.isLogon)).update((!param.isLogon))
    // )
}