package com.example

data class Item(val id: Int, var amount: Int, val price: Int) {
    override fun toString(): String = "$id 1 $price"
}

data class Purchase(val bundle: Set<Item>, val totalPrice: Int) : Comparable<Purchase> {
    override fun toString(): String =
        "${bundle.joinToString(" ") { "${it.id} ${it.amount}" }} $totalPrice"

    override fun compareTo(other: Purchase): Int = totalPrice.compareTo(other.totalPrice)
}
