package com.example

import com.google.common.collect.TreeMultimap

object KnapsackParser : Parser {
    override fun toString(): String = "Knapsack parser"

    override fun parse(prices: String, promotions: String): String {
        val sb = StringBuilder()
        return "Work in progress"
    }

    private fun knapsack(priceMap: Map<Int, Item>, purchaseList: List<Purchase>) {
    }

    private fun knapsack(X: IntArray, Y: IntArray, n: Int, m: Int): Int {
        if (m <= 0 || n <= 0) {
            return 0
        }
        val previous = knapsack(X, Y, n, m - 1)
        if (X[m - 1] > n) {
            return previous
        }
        val current = Y[m - 1] + knapsack(X, Y, n - X[m - 1], m - 1)
        return maxOf(previous, current)
    }
}

object GreedyParser : Parser {
    override fun toString(): String = "Greedy parser"

    override fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }
        val priceMap = mutableMapOf<Int, Item>()
        val promotionMultimap = TreeMultimap.create<Float, Purchase>()

        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            priceMap += parts[0].toInt() to Item(
                parts[0].toInt(),
                parts[1].toInt(),
                parts[2].toInt()
            )
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val items = linkedSetOf<Item>()
            var id: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (id != null) {
                    items += Item(id!!, it.toInt(), priceMap[id!!]!!.price)
                    id = null
                } else {
                    id = it.toInt()
                }
            }
            val totalPrice = parts.last().toInt()
            promotionMultimap.put(
                items.sumOf { it.amount * it.price }.toFloat() - totalPrice,
                Purchase(items, totalPrice)
            )
        }

        val sb = StringBuilder()
        val keysIterator = priceMap.keys.iterator()
        var spent = 0
        while (priceMap.values.any { it.amount > 0 } && keysIterator.hasNext()) {
            val item = priceMap[keysIterator.next()]!!
            val bestPromotion = promotionMultimap.values()
                .lastOrNull { it.bundle.any { it.id == item.id && it.amount <= item.amount } }
                ?: continue
            // use promotions
            while (bestPromotion.bundle.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                spent += bestPromotion.totalPrice
                bestPromotion.bundle.forEach { priceMap[it.id]!!.amount -= it.amount }
                sb.appendLine(bestPromotion.toString())
            }
            // deduce leftover with non-promotions
            while (item.amount > 0) {
                spent += item.price
                item.amount--
                sb.appendLine(item.toString())
            }
        }
        return sb.append(spent).toString()
    }
}

interface Parser {
    fun parse(prices: String, promotions: String): String

    fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = lines().first().trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
