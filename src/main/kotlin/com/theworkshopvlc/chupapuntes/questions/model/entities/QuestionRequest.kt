package com.theworkshopvlc.chupapuntes.questions.model.entities

data class QuestionRequest(
  val title: String,
  val description: String,
  val categories: Set<String>
)
