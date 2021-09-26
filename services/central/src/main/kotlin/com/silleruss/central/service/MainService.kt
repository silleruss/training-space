package com.silleruss.central.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class MainService(
    @Qualifier("TempApi") private val tempWebClient: WebClient
) {

    fun get(): Mono<String> {
        return tempWebClient
            .get()
            .uri("/")
            .retrieve()
            .bodyToMono(String::class.java)
    }

}