package com.silleruss.central.core.extensions

import arrow.core.Either
import java.util.*

inline fun <L, R: Any> Optional<R>.toEither(item: () -> L): Either<L, R> = if (isPresent) Either.Right(get()) else Either.Left(item())
