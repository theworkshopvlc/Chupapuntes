package com.theworkshopvlc.chupapuntes.questions.model.entities

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category

fun Question.toResponse(): QuestionResponse =
  QuestionResponse(
    title = this.title,
    question = this.description,
    categories = this.categories.map(Category::title).toSet(),
    createdAt = this.createdAt,
    id = this.id
  )

fun QuestionRequest.toEntity(): Question =
  Question(
    title = this.title,
    description = this.description
  )
