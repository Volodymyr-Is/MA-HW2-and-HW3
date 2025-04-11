package com.example.myapp4.data.formater

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeFormater {
    private val formater = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    fun formatToDate(timestamp: Long): String{
        return formater.format(Date(timestamp))
    }
}