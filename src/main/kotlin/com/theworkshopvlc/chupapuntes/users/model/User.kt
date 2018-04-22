package com.theworkshopvlc.chupapuntes.users.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val uid: Long,
  val username: String,
  val password: String
)
