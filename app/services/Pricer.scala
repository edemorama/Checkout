package services

/**
  * Created by edem on 12/07/16.
  */
trait Pricing {
  def itemPrices : Map[String, Double]
  def calcTotalItemPrice(itemsList : List[String], applyOffer: Boolean = false) : Double
}

object AppleAndOrangePricing extends Pricing {
  override val itemPrices : Map[String, Double] = Map("apple" -> 0.60, "orange" -> 0.25).withDefaultValue(0.0)

  override def calcTotalItemPrice(itemsList : List[String], applyOffer: Boolean = false) : Double = {
    // apply offer to produce a discount quantity
    def offer(quantity: Int, m : Int, n: Int ) =
      if (applyOffer) quantity % m + quantity/m * n else quantity

    // get apple and orange quantities
    val (appleQty, orangeQty) = itemsList.foldLeft((0,0))((acc, item) => item match {
      case "apple" => (acc._1 + 1, acc._2)
      case "orange" => (acc._1, acc._2 + 1)
      case _ =>  (acc._1, acc._2)
    })

    //apply apple and orange offers to get new quantities
    val (appleOfferQty, orangeOfferQty) = (offer(appleQty, 2, 1), offer(orangeQty, 3, 2))

    appleOfferQty * itemPrices("apple") +  orangeOfferQty * itemPrices("orange")
  }
}
