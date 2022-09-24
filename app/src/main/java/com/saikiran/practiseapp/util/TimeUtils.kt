package com.saikiran.practiseapp.util

object TimeUtils {
    fun getDiffMinutes(d1: Long, d2: Long): Int {
        return ((d2- d1) / (1000 * 60) % 60).toInt()
    }
}