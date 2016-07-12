import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {
  import services.AppleAndOrangePricing._

  "Application" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

    "Price 1 orange and and 2 apples" in {
      val itemsList = "apple,orange,Apple".toLowerCase.split(",").toList
      calcTotalItemPrice(itemsList) mustBe 1.45
    }
  }
}
