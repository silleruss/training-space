package com.silleruss.central.controller.user

import com.silleruss.central.model.user.UserDto
import com.silleruss.central.model.user.CreateUserRequest
import com.silleruss.central.model.user.UpdateUserRequest
import com.silleruss.central.model.user.UpdateUserResponse
import com.silleruss.central.service.user.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {

    @GetMapping
    fun get(@RequestParam id: Int): Mono<UserDto> {
        return service.get(id)
    }

    @PostMapping
    fun create(@RequestBody body: CreateUserRequest): Mono<UserDto> {
        return service.create(body)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody body: UpdateUserRequest
    ): UpdateUserResponse {
        return service.update(id, body)
    }

}