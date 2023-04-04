package com.example

import kotlin.test.Test
import kotlin.test.assertFails

class ParserTest {
    @Test
    fun errors() {
        assertFails { BruteForceParser.parse("", "Hello World") }
        assertFails { BruteForceParser.parse("Hello World", "") }
    }
}
