package com.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class ParserTest {
    @Test
    fun errors() {
        assertFails { Parser.parse("", "Hello World") }
        assertFails { Parser.parse("Hello World", "") }
        assertFails { Parser.parse("1\n2", "1\n2\n3") }
    }

    @Test
    fun test() {
        assertEquals("", Parser.parse("1\n2\n3", "1\n2\n3"))
    }
}
