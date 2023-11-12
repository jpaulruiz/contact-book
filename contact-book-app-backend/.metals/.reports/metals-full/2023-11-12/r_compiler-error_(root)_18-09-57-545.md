file:///D:/Practice/ScalaXVue/Contact-book-2/Contact-book-2/contact-book-app-backend/app/security/Authenticator.scala
### java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/User1/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10-sources.jar!/scala/Option.scala

occurred in the presentation compiler.

action parameters:
offset: 1638
uri: file:///D:/Practice/ScalaXVue/Contact-book-2/Contact-book-2/contact-book-app-backend/app/security/Authenticator.scala
text:
```scala
package models.security

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import models.repo.UserRepo
import scala.concurrent._
import play.api.libs.json._
import play.api.libs._

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
        println("[Action] - Get session: token-> " + request.session.get("authToken"))

        if getAuth(request) then
            request.session.get("authToken") match
                case Some(authToken) if authToken == request.headers.get("authToken").get =>
                    block(new UserRequest(request.session.get("authToken"), request))
                case _ => Future.successful(Results.Unauthorized)
        else Future.successful(Results.Unauthorized)
    }

    def getAuth[A](request: Request[A]): Boolean = {
        // val cookieValue = request.cookies.get("PLAY3").map(_.value)
        // println(cookieValue)
        // request.headers.get("authToken") match 
        //     case Some(authToken) => true
        //     case None => false
        request.cookies.get("PLAY3").map(_.value) m@@
    }
}
```



#### Error stacktrace:

```
java.base/sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
	java.base/sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
	java.base/sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:229)
	java.base/java.nio.file.Path.of(Path.java:147)
	java.base/java.nio.file.Paths.get(Paths.java:69)
	scala.meta.io.AbsolutePath$.apply(AbsolutePath.scala:60)
	scala.meta.internal.metals.MetalsSymbolSearch.$anonfun$definitionSourceToplevels$2(MetalsSymbolSearch.scala:62)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsSymbolSearch.definitionSourceToplevels(MetalsSymbolSearch.scala:61)
	scala.meta.internal.pc.completions.CaseKeywordCompletion$.sortSubclasses(MatchCaseCompletions.scala:306)
	scala.meta.internal.pc.completions.CaseKeywordCompletion$.matchContribute(MatchCaseCompletions.scala:254)
	scala.meta.internal.pc.completions.Completions.advancedCompletions(Completions.scala:375)
	scala.meta.internal.pc.completions.Completions.completions(Completions.scala:183)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:86)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:123)
```
#### Short summary: 

java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/User1/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10-sources.jar!/scala/Option.scala