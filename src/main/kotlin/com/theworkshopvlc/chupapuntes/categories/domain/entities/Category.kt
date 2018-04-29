package com.theworkshopvlc.chupapuntes.categories.domain.entities

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import javax.persistence.Entity
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
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @ManyToMany
  @JoinTable(name = "category_question",
    joinColumns = [JoinColumn(name = "category_id")],
    inverseJoinColumns = [JoinColumn(name = "question_id")])
  val questions: Set<Question> = emptySet(),

  val title: String = "Hola"
)
