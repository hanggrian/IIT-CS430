package com.example

import com.google.common.collect.TreeMultimap

enum class ParserFactory {
    GREEDY {
        override fun create(): Parser = GreedyParser()
        override fun toString(): String = "Greedy parser"
    },
    RECURSIVE {
        override fun create(): Parser = RecursiveParser()
        override fun toString(): String = "Recursive parser"
    };

    abstract fun create(): Parser
}

class RecursiveParser : Parser() {
    val priceMap = mutableMapOf<Int, Price>()
    var priceMapTemp: MutableMap<Int, Price>? = null
    val promotionList = mutableListOf<Promotion>()

    override fun onAddPrice(price: Price) {
        priceMap += price.id to price
    }

    override fun onAddPromotion(promotion: Promotion) {
        promotionList += promotion
    }

    override fun onGetPrice(id: Int): Price = priceMap[id]!!

    override fun onParse(): String {
        val sb = StringBuilder()
        // use promotions
        priceMapTemp = null
        val spent = recursePromotions(priceMap, sb, 0)
        // deduce leftover with non-promotions
        priceMapTemp?.forEach { (_, price) ->
            if (price.amount > 0) {
                sb.appendLine(price.toString())
                // TODO: unoptimized
                // spent += price.worth
                // price.amount = 0
            }
        }
        return sb.append(spent.formatted).toString()
    }

    private fun recursePromotions(priceMap: Map<Int, Price>, sb: StringBuilder, i: Int): Double {
        var sum = priceMap.values.sumOf { it.worth }
        for (j in i until promotionList.size) {
            val promotion = promotionList[j]
            var temp: MutableMap<Int, Price>? = mutableMapOf()
            for (price in priceMap.values) {
                val itemsToDeduce = promotion.items.filter { it.id == price.id }.sumOf { it.amount }
                if (price.amount < itemsToDeduce || itemsToDeduce == 0) {
                    temp = null
                    break
                }
                temp!![price.id] = Price(price.id, price.amount - itemsToDeduce, price.price)
            }
            if (temp != null) {
                val s = promotion.toString()
                if (!sb.startsWith(s)) {
                    sb.appendLine(s)
                }
                priceMapTemp = temp
                sum = minOf(sum, promotion.price + recursePromotions(temp, sb, i))
            }
        }
        return sum
    }
}

class GreedyParser : Parser() {
    val priceMap = mutableMapOf<Int, Price>()
    val promotionMultimap = TreeMultimap.create<Double, Promotion>()

    override fun onAddPrice(price: Price) {
        priceMap += price.id to price
    }

    override fun onAddPromotion(promotion: Promotion) {
        promotionMultimap.put(
            promotion.items.sumOf { it.worth } - promotion.price,
            promotion
        )
    }

    override fun onGetPrice(id: Int): Price = priceMap[id]!!

    override fun onParse(): String {
        val sb = StringBuilder()
        val keysIterator = priceMap.keys.iterator()
        var spent = 0.0
        while (priceMap.values.any { it.amount > 0 } && keysIterator.hasNext()) {
            val item = priceMap[keysIterator.next()]!!
            val bestPromotion = promotionMultimap.values().lastOrNull { purchase ->
                purchase.items.any { it.id == item.id && it.amount <= item.amount }
            } ?: continue
            // use promotions
            while (bestPromotion.items.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                sb.appendLine(bestPromotion.toString())
                spent += bestPromotion.price
                bestPromotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
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
    abstract fun onAddPrice(price: Price)
    abstract fun onAddPromotion(promotion: Promotion)
    abstract fun onGetPrice(id: Int): Price
    abstract fun onParse(): String

    fun parse(sample: Sample): String = parse(sample.prices, sample.promotions)

    fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }

        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            onAddPrice(Price(parts[0].toInt(), parts[1].toInt(), parts[2].toDouble()))
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val items = mutableListOf<Promotion.Item>()
            var id: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (id != null) {
                    items += Promotion.Item(id!!, it.toInt(), onGetPrice(id!!).price)
                    id = null
                } else {
                    id = it.toInt()
                }
            }
            onAddPromotion(Promotion(items, parts.last().toDouble()))
        }
        return onParse()
    }

    private fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = lines().first().trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
