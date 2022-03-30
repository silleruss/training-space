package com.silleruss.central.model.user

data class CreateUserRequest(
    val nickname: String,
    val email: String
)

data class UpdateUserRequest(
    val nickname: String,
    val email: String
)