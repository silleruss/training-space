package com.silleruss.central.core

import arrow.core.Either
import java.util.*

sealed class Either {

    companion object {

        private fun <L> fromLeft(left: L): Either<L, Nothing> = Either.Left(left)
        private fun <R> fromRight(right: R): Either<Nothing, R> = Either.Right(right)

        fun <R> Optional<R>.toEither(): Either<Throwable, R> = if (isPresent) { fromRight(get()) } else fromLeft(RuntimeException())

    }

}
