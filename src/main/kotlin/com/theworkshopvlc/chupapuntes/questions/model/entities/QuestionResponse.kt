package com.theworkshopvlc.chupapuntes.questions.model.entities

data class QuestionResponse(
  val title: String,
  val question: String,
  val categories: Set<String>,
  val createdAt: Long,
  val id: Long
)
