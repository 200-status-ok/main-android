package com.example.haminjast.data

import kotlinx.coroutines.delay

object LoginRepository {

    suspend fun sendOTP(phoneNumber: String):Result<Unit> {
        delay(1500)
        return Result.success(Unit)
    }
}