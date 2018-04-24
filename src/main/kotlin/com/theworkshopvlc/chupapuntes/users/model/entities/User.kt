package com.theworkshopvlc.chupapuntes.users.model.entities

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "Users")
data class User(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val uid: Long = 0,

  val username: String = "",

  val password: String = "",

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  val role: Role = Role()
)

fun User.mapToSecurity(): SecurityUser =
  SecurityUser(username, password, listOf(role).map(Role::toAuthority))
