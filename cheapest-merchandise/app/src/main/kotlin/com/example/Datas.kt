package com.example

data class Item(
    val price: Int,
    var amount: Int,
)

data class Step(
    val id: Int,
    val amount: Int,
)

data class Purchase(
    val steps: Set<Step>,
    val price: Int,
)
