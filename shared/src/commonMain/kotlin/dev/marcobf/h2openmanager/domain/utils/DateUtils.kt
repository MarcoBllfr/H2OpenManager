package dev.marcobf.h2openmanager.domain.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun todayEpochDays(): Long =
    Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date
        .toEpochDays()
        .toLong()

fun isOverdue(dueDate: Long): Boolean =
    dueDate < todayEpochDays()

fun addDays(epochDays: Long, days: Int): Long =
    LocalDate.fromEpochDays(epochDays.toInt())
        .plus(days, DateTimeUnit.DAY)
        .toEpochDays()
        .toLong()
