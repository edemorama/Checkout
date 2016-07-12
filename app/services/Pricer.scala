package services

/**
  * Created by edem on 12/07/16.
  */
trait Pricing {
  def itemPrices : Map[String, Double]
  def calcTotalItemPrice(itemsList : List[String]) : Double
}

object AppleAndOrangePricing extends Pricing {
  override val itemPrices : Map[String, Double] = Map("apple" -> 0.60, "orange" -> 0.25).withDefaultValue(0.0)

  override def calcTotalItemPrice(itemsList : List[String]) : Double =
    itemsList.foldLeft(0.0)((acc, item) => acc + itemPrices(item))
}
