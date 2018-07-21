package com.theworkshopvlc.chupapuntes.users.model.entities

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
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
  val role: Role = Role(),

  @OneToMany(
    mappedBy = "author",
    cascade = [(CascadeType.ALL)],
    orphanRemoval = true
  )
  val questions: Set<Question> = emptySet()
)

fun User.mapToSecurity(): SecurityUser =
  SecurityUser(username, password, listOf(role).map(Role::toAuthority))
