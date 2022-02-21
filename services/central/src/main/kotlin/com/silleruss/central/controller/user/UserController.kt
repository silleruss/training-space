package com.silleruss.central.controller.user

import com.silleruss.central.model.UserDto
import com.silleruss.central.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {

    @GetMapping
    fun temp(@RequestParam id: Int): Mono<UserDto> {
        return service.get(id)
    }

}