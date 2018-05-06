package com.theworkshopvlc.chupapuntes.extensions

import org.apache.commons.lang3.StringUtils

fun String.toSnakeCase(): String = this.split(" ").joinToString("_")

fun String.stripAccents(): String = StringUtils.stripAccents(this)
