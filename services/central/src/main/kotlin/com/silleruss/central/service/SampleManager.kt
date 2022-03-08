package com.silleruss.central.service

import com.silleruss.central.core.SampleStatus
import org.springframework.stereotype.Component

@Component
class SampleManager {

    // sample about nullable
    fun sampleNullableTesting() {
        val nullableStatus: String? = null
        val expired = "expired"

        SampleStatus.ofNullable(nullableStatus)
        SampleStatus.of(expired)
    }

}