package com.silleruss.central.core.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
object EntityNotFoundException : RuntimeException("Entity Not Found Exception")