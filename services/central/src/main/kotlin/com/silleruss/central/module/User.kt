package com.silleruss.central.module

import java.time.LocalDateTime

// FIXME: set jpa and entity
data class User(
    val id: Int = 0,
    val nickname: String,
    val email: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
