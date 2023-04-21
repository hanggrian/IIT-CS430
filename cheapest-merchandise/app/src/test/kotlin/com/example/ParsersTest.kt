package com.example

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test
import kotlin.test.assertFails

class ParsersTest {
    @Test
    fun errors() {
        val parser = GreedyParser()
        assertFails { parser.parse("", "Hello World") }
        assertFails { parser.parse("Hello World", "") }
    }

    @Test
    fun greedy() {
        val parser = GreedyParser()
        assertThat(parser.parse(Sample.PDF).split('\n')).containsExactly(
            "7 1 8 2 10",
            "7 2 2",
            "14"
        )
        assertThat(parser.parse(Sample.BLACKBOARD1).split('\n')).containsExactly(
            "13 1 3 2 20",
            "3 2 3",
            "26"
        )
        assertThat(parser.parse(Sample.BLACKBOARD2).split('\n')).containsExactly(
            "9 2 10",
            "9 2 10",
            "1 4 8",
            "1 1 2.5",
            "30.5"
        )
    }

    @Test
    fun recursive() {
        val parser = RecursiveParser()
        assertThat(parser.parse(Sample.PDF).split('\n')).containsExactly(
            "7 1 8 2 10",
            "7 2 2",
            "14"
        )
        assertThat(parser.parse(Sample.BLACKBOARD1).split('\n')).containsExactly(
            "13 1 3 2 20",
            "3 2 3",
            "26"
        )
        assertThat(parser.parse(Sample.BLACKBOARD2).split('\n')).containsExactly(
            "9 2 10",
            "9 2 10",
            "1 4 8",
            "1 1 2.5",
            "30.5"
        )
    }
}
