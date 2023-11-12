package pekko

import pekko.ManagerActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class ConfiguredActor extends AbstractModule with PekkoGuiceSupport {
  override def configure = {
    bindActor[ManagerActor]("manager-actor")
  }
}