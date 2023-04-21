package com.example

data class Price(
    val id: Int,
    override var amount: Int,
    override val price: Double
) : Merchandise {

    override fun toString(): String = "$id $amount ${price.formatted}"
}

data class Promotion(
    val items: List<Item>,
    val price: Double
) : Comparable<Promotion> {

    data class Item(
        val id: Int,
        override val amount: Int,
        override val price: Double
    ) : Merchandise

    val saving: Double = items.sumOf { it.worth } - price

    override fun toString(): String =
        "${items.joinToString(" ") { "${it.id} ${it.amount}" }} ${price.formatted}"

    override fun compareTo(other: Promotion): Int = price.compareTo(other.price)
}

private interface Merchandise {
    val amount: Int
    val price: Double
    val worth: Double get() = amount * price
}
