package com.theworkshopvlc.chupapuntes.users.model.entities

import javax.persistence.*

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
