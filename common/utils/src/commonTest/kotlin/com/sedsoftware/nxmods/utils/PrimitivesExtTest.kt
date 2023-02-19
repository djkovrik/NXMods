package com.sedsoftware.nxmods.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class PrimitivesExtTest {

    @Test
    fun prettify_Long_test() {
        assertEquals(999L.prettify(), "999")
        assertEquals(1000L.prettify(), "1k")
        assertEquals(1100L.prettify(), "1.1k")
        assertEquals(1234L.prettify(), "1.2k")
        assertEquals(1999L.prettify(), "1.9k")
        assertEquals(12345L.prettify(), "12.3k")
        assertEquals(123456L.prettify(), "123.4k")

        assertEquals(999999L.prettify(), "999.9k")
        assertEquals(1000000L.prettify(), "1M")
        assertEquals(1100000L.prettify(), "1.1M")
        assertEquals(1234567L.prettify(), "1.2M")
        assertEquals(1999999L.prettify(), "1.9M")
        assertEquals(12345678L.prettify(), "12.3M")
        assertEquals(123456789L.prettify(), "123.4M")
        assertEquals(999999999L.prettify(), "999.9M")

        assertEquals(1000000000L.prettify(), "1bn")
        assertEquals(1234567890L.prettify(), "1.2bn")
        assertEquals(12345678901L.prettify(), "12.3bn")
        assertEquals(123456789021L.prettify(), "123.4bn")
    }
}
