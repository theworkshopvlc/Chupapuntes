package com.theworkshopvlc.chupapuntes.users.model.entities

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "Roles")
data class Role(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  val name: String = "",

  @OneToMany(
    mappedBy = "role",
    cascade = [(CascadeType.ALL)],
    orphanRemoval = true
  )
  val users: Set<User> = emptySet()

)

class Authority(
  val id: Long,
  val name: String
) : GrantedAuthority {
  override fun getAuthority(): String = name
}

fun Role.toAuthority() = Authority(id, name)
