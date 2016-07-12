import org.scalatestplus.play._
import play.api.libs.ws.WSClient
import play.api.test.Helpers._
import scala.concurrent.Await
import scala.concurrent.duration._


/**
 * Fire up the application and perform a checkout pricing of some items
 */
class IntegrationSpec extends PlaySpec with OneServerPerTest {

  "Application" should {

    "be able to correctly price a checkout list" in {
      val items = "Apple,Orange,APPLE"
      val wsClient = app.injector.instanceOf[WSClient]
      val result = Await.result(wsClient.url(s"http://localhost:$port/price/$items").get(), 5.seconds)

      result.status mustBe OK
      result.body mustEqual "0.85"
    }
  }
}
