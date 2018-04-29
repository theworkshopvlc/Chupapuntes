package com.theworkshopvlc.chupapuntes.questions.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Question(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long,

  val title: String,

  val description: String,

  val createdAt: Long = System.currentTimeMillis()
)
