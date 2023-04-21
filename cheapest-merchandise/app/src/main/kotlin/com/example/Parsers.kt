package com.example

import com.google.common.collect.Multimap
import com.google.common.collect.TreeMultimap

class DfsParser : Parser() {
    override fun toString(): String = "DFS parser"

    override fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String {
        val sb = StringBuilder()
        val spent = dfs(priceMap, promotionMultimap.values().toList(), 0)
        return sb.append(spent.formatted).toString()
    }

    private fun dfs(
        priceMap: Map<Int, Price>,
        promotionList: List<Promotion>,
        i: Int
    ): Double {
        var sum = priceMap.values.sumOf { it.worth }
        for (j in i until promotionList.size) {
            val promotion = promotionList[j]
            if (promotion.items.all { priceMap[it.id]!!.amount >= it.amount }) {
                // use special
                promotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
                sum = minOf(sum, promotion.price + dfs(priceMap, promotionList, i))
                // backtrack
                promotion.items.forEach { priceMap[it.id]!!.amount += it.amount }
            }
        }
        return sum
    }
}

class GreedyParser : Parser() {
    override fun toString(): String = "Greedy parser"

    override fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String {
        val sb = StringBuilder()
        val keysIterator = priceMap.keys.iterator()
        var spent = 0.0
        while (keysIterator.hasNext()) {
            val item = priceMap[keysIterator.next()]!!
            val bestPromotion = promotionMultimap.values().lastOrNull { purchase ->
                purchase.items.any { it.id == item.id && it.amount <= item.amount }
            }
            // use promotions
            if (bestPromotion != null) {
                while (bestPromotion.items.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                    sb.appendLine(bestPromotion.toString())
                    spent += bestPromotion.price
                    bestPromotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
                }
            }
            // deduce leftover with non-promotions
            if (item.amount > 0) {
                sb.appendLine(item.toString())
                spent += item.worth
                item.amount = 0
            }
        }
        return sb.append(spent.formatted).toString()
    }
}

abstract class Parser {
    abstract fun onParse(
        priceMap: Map<Int, Price>,
        promotionMultimap: Multimap<Double, Promotion>
    ): String

    fun parse(sample: Sample): String = parse(sample.prices, sample.promotions)

    fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }

        val priceMap = mutableMapOf<Int, Price>()
        val promotionMultimap = TreeMultimap.create<Double, Promotion>()
        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            priceMap += parts[0].toInt() to Price(
                parts[0].toInt(),
                parts[1].toInt(),
                parts[2].toDouble()
            )
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val items = mutableListOf<Promotion.Item>()
            var id: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (id != null) {
                    items += Promotion.Item(id!!, it.toInt(), priceMap[id]!!.price)
                    id = null
                } else {
                    id = it.toInt()
                }
            }
            val price = parts.last().toDouble()
            val saving = items.sumOf { it.worth } - price
            promotionMultimap.put(saving, Promotion(items, price))
        }
        return onParse(priceMap, promotionMultimap)
    }

    private fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = lines().first().trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
