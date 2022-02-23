package com.silleruss.central.model.users

data class CreateUserRequest(
    val nickname: String,
    val email: String
)

data class UpdateUserRequest(
    val nickname: String,
    val email: String
)