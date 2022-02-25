package com.silleruss.central.model.users

import arrow.core.Either
import com.silleruss.central.model.UserDto
import reactor.core.publisher.Mono

typealias UpdateUserResponse = Either<Throwable, Mono<UserDto>>