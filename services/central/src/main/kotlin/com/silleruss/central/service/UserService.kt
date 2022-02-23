package com.silleruss.central.service

import com.silleruss.central.model.UserDto
import com.silleruss.central.model.users.CreateUserRequest
import com.silleruss.central.model.users.UpdateUserRequest
import com.silleruss.central.module.User
import com.silleruss.central.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
    private val repository: UserRepository
) {

    fun get(id: Int): Mono<UserDto> {
        return repository
            .findById(id)
            .map { it.convert().toMono() }
            .orElseThrow { RuntimeException() }
    }

    fun create(body: CreateUserRequest): Mono<UserDto> {
        TODO()
    }

    fun update(id: Int, body: UpdateUserRequest): Mono<UserDto> {
        TODO()
    }

    private fun User.convert(): UserDto = UserDto(
        nickname = nickname,
        email = email
    )

}