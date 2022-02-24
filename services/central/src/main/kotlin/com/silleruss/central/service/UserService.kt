package com.silleruss.central.service

import arrow.core.Either
import com.silleruss.central.core.Either.Companion.toEither
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
        val user = body.toUser()
        val saved = repository.save(user)

        return saved.convert().toMono()
    }

    fun update(id: Int, body: UpdateUserRequest): Either<Throwable, Mono<UserDto>> {
        val user = repository.findById(id)
        val updated = user.map { body.toEntity(it.id) }
        val saved = updated.map { repository.save(it) }

        return saved
            .map { it.convert().toMono() }
            .toEither()
    }

    private fun CreateUserRequest.toUser(): User = User(
        nickname = nickname,
        email = email
    )

    private fun UpdateUserRequest.toEntity(id: Int): User = User(
        id = id,
        nickname = nickname,
        email = email
    )

    private fun User.convert(): UserDto = UserDto(
        nickname = nickname,
        email = email
    )

}