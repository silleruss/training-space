package com.silleruss.central.core

import java.util.*

// 테스트를 위한 샘플 enum class
enum class SampleStatus {

    ACTIVE,
    PENDING,
    EXPIRED,
    ;

    override fun toString(): String {
        return super.toString().lowercase()
    }

    companion object {

        // function param is nullable
        fun ofNullable(value: String?): Optional<SampleStatus> {
            return Optional.ofNullable(values().firstOrNull { it.toString() == value })
        }

        // function param is not nullable
        fun of(value: String): Optional<SampleStatus> {
            return Optional.ofNullable(values().firstOrNull { it.toString() == value })
        }

    }

}