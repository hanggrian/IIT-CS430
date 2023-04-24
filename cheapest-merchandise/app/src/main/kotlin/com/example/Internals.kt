package com.example

import java.text.DecimalFormat

private val DECIMAL_FORMAT = DecimalFormat().apply { isDecimalSeparatorAlwaysShown = false }

internal val Double.formatted: String get() = DECIMAL_FORMAT.format(this)
