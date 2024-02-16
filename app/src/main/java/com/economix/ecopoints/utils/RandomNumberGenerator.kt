package com.economix.ecopoints.utils

import kotlin.random.Random

object RandomNumberGenerator {

    fun generateRandomNumber(start: Int, endInclusive: Int): Int =
        Random.nextInt(start, endInclusive + 1)
}
