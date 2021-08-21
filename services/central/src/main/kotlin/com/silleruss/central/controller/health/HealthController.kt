package com.silleruss.central.controller.health

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/health")
class HealthController {

    @GetMapping
    fun health(): Mono<String> = Mono.just("on")

}