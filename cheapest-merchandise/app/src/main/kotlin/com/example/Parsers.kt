package com.example

object BruteForceParser : Parser {
    override fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }
        val itemMap = mutableMapOf<Int, Item>()
        val purchaseList = mutableListOf<Purchase>()

        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            itemMap += parts[0].toInt() to Item(parts[1].toInt(), parts[2].toInt())
            purchaseList += Purchase(setOf(Step(parts[0].toInt(), 1)), parts[1].toInt())
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val linkedSet = linkedSetOf<Step>()
            var current: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (current != null) {
                    linkedSet += Step(current!!, it.toInt())
                    current = null
                } else {
                    current = it.toInt()
                }
            }
            purchaseList += Purchase(linkedSet, parts.last().toInt())
        }

        return ""
    }
}

interface Parser {
    fun parse(prices: String, promotions: String): String

    fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = substringAfter('\n').trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
