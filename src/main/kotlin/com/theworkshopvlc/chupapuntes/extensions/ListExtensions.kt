package com.theworkshopvlc.chupapuntes.extensions

fun <T> List<T?>.anyIsNull(): Boolean = this.any { it == null }
