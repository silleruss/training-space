package com.silleruss.central.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration(
    @Value("\${central.endpoint.temp-api}") private val mainUrl: String,
    private val webClientBuilder: WebClient.Builder
) {

    @Bean("TempApi")
    fun webClientApi(): WebClient {
        return webClientBuilder
            .baseUrl(mainUrl)
            .build()
    }

}