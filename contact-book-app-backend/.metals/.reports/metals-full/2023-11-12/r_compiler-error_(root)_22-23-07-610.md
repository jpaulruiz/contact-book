file:///D:/Practice/ScalaXVue/Contact-book-2/Contact-book-2/contact-book-app-backend/app/pekko/ContactActor.scala
### java.lang.AssertionError: NoDenotation.owner

occurred in the presentation compiler.

action parameters:
offset: 417
uri: file:///D:/Practice/ScalaXVue/Contact-book-2/Contact-book-2/contact-book-app-backend/app/pekko/ContactActor.scala
text:
```scala
package pekko

import org.apache.pekko.actor._
import play.api.libs.json.JsValue

object ContactActor:
    def props(out: ActorRef, manager: ActorRef) = Props(new ContactActor(out, manager))

class ContactActor(out: ActorRef, manager: ActorRef) extends Actor:
    import ContactActor._
    def receive: Actor.Receive = 
        case js: JsValue => 
            manager ! js
        case futureJs: Future[@@]
```



#### Error stacktrace:

```
dotty.tools.dotc.core.SymDenotations$NoDenotation$.owner(SymDenotations.scala:2582)
	scala.meta.internal.pc.SignatureHelpProvider$.isValid(SignatureHelpProvider.scala:83)
	scala.meta.internal.pc.SignatureHelpProvider$.notCurrentApply(SignatureHelpProvider.scala:94)
	scala.meta.internal.pc.SignatureHelpProvider$.$anonfun$1(SignatureHelpProvider.scala:48)
	scala.collection.StrictOptimizedLinearSeqOps.loop$3(LinearSeq.scala:280)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile(LinearSeq.scala:282)
	scala.collection.StrictOptimizedLinearSeqOps.dropWhile$(LinearSeq.scala:278)
	scala.collection.immutable.List.dropWhile(List.scala:79)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:48)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.AssertionError: NoDenotation.owner