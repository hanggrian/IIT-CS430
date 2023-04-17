package com.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class ParsersTest {
    @Test
    fun errors() {
        assertFails { GreedyParser.parse("", "Hello World") }
        assertFails { GreedyParser.parse("Hello World", "") }
    }

    @Test
    fun greedy() {
        val result = GreedyParser.parse(
            """
            2
            7 3 2
            8 2 5
            """.trimIndent(),
            """
            2
            1 7 3 5
            2 7 1 8 2 10
            """.trimIndent()
        )
        assertEquals(
            """
            7 1 8 2 10
            7 1 2
            7 1 2
            14
            """.trimIndent(),
            result
        )
    }

    @Test
    fun knapsack() {
    }
}
