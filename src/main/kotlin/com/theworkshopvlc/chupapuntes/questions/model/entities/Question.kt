package com.theworkshopvlc.chupapuntes.questions.model.entities

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Question(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  val title: String = "",

  val description: String = "",

  @ManyToMany(mappedBy = "questions")
  val categories: Set<Category> = emptySet(),

  val createdAt: Long = System.currentTimeMillis()
) {
  companion object {
    const val MAX_TITLE_LENGTH = 120
  }
}
