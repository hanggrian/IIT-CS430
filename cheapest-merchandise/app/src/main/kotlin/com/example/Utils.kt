package com.example

import java.text.DecimalFormat

private val DECIMAL_FORMAT = DecimalFormat().apply { isDecimalSeparatorAlwaysShown = false }

val Double.formatted: String get() = DECIMAL_FORMAT.format(this)
