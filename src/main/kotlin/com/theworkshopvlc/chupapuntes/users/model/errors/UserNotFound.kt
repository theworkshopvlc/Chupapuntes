package com.theworkshopvlc.chupapuntes.users.model.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFound : RuntimeException("User not found")
