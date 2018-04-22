package com.theworkshopvlc.chupapuntes.users.model.usecases

import com.theworkshopvlc.chupapuntes.users.model.User
import com.theworkshopvlc.chupapuntes.users.model.errors.UserNotFound
import com.theworkshopvlc.chupapuntes.users.persistence.IUserDAO
import org.springframework.stereotype.Component

@Component
class GetUserByUsername(private val userDAO: IUserDAO) {
  fun execute(username: String): User =
    userDAO.findByUsername(username) ?: throw UserNotFound()
}
