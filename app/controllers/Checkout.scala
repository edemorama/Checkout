package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}

/**
  * Created by edem on 12/07/16.
  */
@Singleton
class Checkout @Inject() extends Controller {
  import services.AppleAndOrangePricing._

  def index = Action {
    Redirect(routes.Checkout.price("Nothing"))
  }
  /**
    * price a comma delimited string of items(currently apple and orange)
    *
    * @param items  e.g. "apple, Orange, orange, APPLE"
    * @return
    */
  def price(items: String) = Action {
    val itemsList = items.toLowerCase.split(",").toList
    val totalItemPrice = calcTotalItemPrice(itemsList, applyOffer = true)
    Ok("%.2f".format(totalItemPrice))
  }
}
