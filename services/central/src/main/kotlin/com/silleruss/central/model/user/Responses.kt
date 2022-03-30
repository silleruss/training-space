package com.silleruss.central.model.user

import arrow.core.Either
import reactor.core.publisher.Mono

typealias UpdateUserResponse = Either<Throwable, Mono<UserDto>>