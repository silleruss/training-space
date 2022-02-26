package com.silleruss.central.service

import arrow.core.Either
import com.silleruss.central.core.exceptions.EntityNotFoundException
import com.silleruss.central.core.extensions.toEither
import com.silleruss.central.model.UserDto
import com.silleruss.central.model.users.CreateUserRequest
import com.silleruss.central.model.users.UpdateUserRequest
import com.silleruss.central.module.User
import com.silleruss.central.repository.UserRepository
import org.hibernate.Session
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
    private val repository: UserRepository,
    @PersistenceContext private val entityManager: EntityManager
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
        val user = repository
            .findById(id)
            .toEither { EntityNotFoundException }

        val updated = user.map { body.toEntity(it.id) }
        val saved = updated.map { repository.save(it) }

        return saved
            .map { it.convert().toMono() }
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

    private fun unwrapSession() = entityManager.unwrap(Session::class.java)

    // batch insert dummy users
    private fun batchInsert(userIds: List<Int>): Unit {
        val values = userIds.map { "${it}, $randomString, ${""}" }

        val sql = """
            insert ignore into users (
                id, nickname, email
            ) values ${values.joinToString { "," }}
        """.trimIndent()

        unwrapSession()
            .createNativeQuery(sql)
            .executeUpdate()
    }

    private fun batchDelete(): Either<Throwable, Long> {
        TODO("Batch delete with hibernate pageable")
    }

    private val randomString = (1..RANDOM_NAME_LENGTH)
        .map { kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("");

    companion object {

        private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        private const val DEFAULT_INSERT_BATCH_SIZE = 100
        private const val RANDOM_NAME_LENGTH = 10

    }

}