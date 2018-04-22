package com.theworkshopvlc.chupapuntes.users.persistence

import com.theworkshopvlc.chupapuntes.users.model.User
import org.springframework.data.repository.CrudRepository

interface IUserDAO : CrudRepository<User, Long> {
  fun findByUsername(username: String): User?
}
