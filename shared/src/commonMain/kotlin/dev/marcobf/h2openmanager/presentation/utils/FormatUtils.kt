package dev.marcobf.h2openmanager.presentation.utils

import kotlinx.datetime.LocalDate

fun formatEpochDays(epochDays: Long): String =
    LocalDate.fromEpochDays(epochDays.toInt()).toString()   // "AAAA-MM-DD"