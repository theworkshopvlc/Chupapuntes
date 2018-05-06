package com.theworkshopvlc.chupapuntes.extensions

import junit.framework.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

  @Test
  fun `toSnakeCase should return a snake cased version of a String`() {
    val original = "Hello how are you?"
    val expected = "Hello_how_are_you?"

    val result = original.toSnakeCase()

    assertEquals(expected, result)
  }
}
