package models.repos

import javax.inject._
import play.api.db.slick._
import slick.jdbc.JdbcProfile

@Singleton
class TemplateRepo @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // TODO: Insert code here
}