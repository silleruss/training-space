package com.silleruss.central.service

import com.silleruss.central.model.UserDto
import com.silleruss.central.module.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(

) {

    fun get(id: Int): Mono<UserDto> {
        return findById(id)
            .map { it.convert().toMono() }
            .orElseThrow { RuntimeException() }
    }

    // FIXME: change when db connect
    private fun findById(id: Int): Optional<User> {
        val dummyUser = User(
            id = id,
            nickname = "john",
            email = "john@test.com",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        return Optional.of(dummyUser)
    }

    private fun User.convert(): UserDto = UserDto(
        nickname = nickname,
        email = email
    )

}