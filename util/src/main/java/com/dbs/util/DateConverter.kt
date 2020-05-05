package com.dbs.util

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    fun convertTimeStampToDateString(timeStamp: Long): String {
        return dateFormat.format(Date(timeStamp))
    }

}