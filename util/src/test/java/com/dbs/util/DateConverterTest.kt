package com.dbs.util

import org.junit.Assert.assertEquals
import org.junit.Test

internal class DateConverterTest {

    @Test
    fun convertTimeStampToDateString_givenTimeStamp_returnDate() {
        val actual = DateConverter.convertTimeStampToDateString(1586404611L)
        assertEquals(actual, "19-01-1970")
    }

}