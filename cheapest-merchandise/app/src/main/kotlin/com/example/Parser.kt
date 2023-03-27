package com.example

object Parser {
    fun parse(input: String, promotions: String): String {
        check(input.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }
        check(input.lines().size == promotions.lines().size) { "Lines length mismatch." }
        return ""
    }
}
