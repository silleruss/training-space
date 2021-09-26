package com.silleruss.central.controller.main

import com.silleruss.central.service.MainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/main")
class MainController(
  private val service: MainService
) {

    @GetMapping
    fun get(): Flux<String> = Flux.just("test", "test")

    @GetMapping("/temp")
    fun temp(): Mono<String> {
        return service.get()
    }

}