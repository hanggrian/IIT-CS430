package com.example

enum class Sample(val prices: String, val promotions: String) {
    PDF(
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
    ),
    BLACKBOARD1(
        """
            2
            13 1 19
            3 4 3
        """.trimIndent(),
        """
            1
            2 13 1 3 2 20
        """.trimIndent()
    ) {
        override fun toString(): String = "Blackboard 1"
    },
    BLACKBOARD2(
        """
            2
            9 4 6
            1 5 2.5
        """.trimIndent(),
        """
            3
            1 1 4 8
            1 9 2 10
        """.trimIndent()
    ) {
        override fun toString(): String = "Blackboard 2"
    }
}
