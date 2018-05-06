package com.theworkshopvlc.chupapuntes.categories.domain.entities

import com.theworkshopvlc.chupapuntes.extensions.stripAccents
import com.theworkshopvlc.chupapuntes.extensions.toSnakeCase
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "Categories")
data class Category(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  val title: String = "Hola",

  val searchTitle: String = title.toLowerCase().toSnakeCase().stripAccents(),

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "category_question",
    joinColumns = [JoinColumn(name = "category_id")],
    inverseJoinColumns = [JoinColumn(name = "question_id")])
  val questions: Set<Question> = emptySet()

) {
  companion object {
    const val MAX_TITLE_LENGTH = 20
  }
}
