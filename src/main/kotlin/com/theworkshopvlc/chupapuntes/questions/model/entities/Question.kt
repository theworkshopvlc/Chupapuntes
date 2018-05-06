package com.theworkshopvlc.chupapuntes.questions.model.entities

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.users.model.entities.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
data class Question(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  val title: String = "",

  val description: String = "",

  @ManyToMany(mappedBy = "questions", fetch = FetchType.EAGER)
  val categories: Set<Category> = emptySet(),

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_uid")
  val author: User = User(),

  val createdAt: Long = System.currentTimeMillis()
) {
  companion object {
    const val MAX_TITLE_LENGTH = 120
  }
}
