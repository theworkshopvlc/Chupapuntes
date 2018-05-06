package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.categories.domain.errors.QuestionValidationsResults
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import org.springframework.stereotype.Component

@Component
class CreateQuestion(private val dao: IQuestionsDAO) {
  fun execute(question: Question): QuestionValidationsResults {
    val validationResult = validateQuestion(question)

    if (validationResult is QuestionValidationsResults.Success)
      dao.save(validationResult.question)

    return validationResult
  }

  private fun validateQuestion(question: Question): QuestionValidationsResults = when {
    question.title.isEmpty() -> QuestionValidationsResults.EmptyTitleError()
    question.description.isEmpty() -> QuestionValidationsResults.EmptyDescriptionError()
    question.title.length > Question.MAX_TITLE_LENGTH -> QuestionValidationsResults.BigTitleError()
    else -> QuestionValidationsResults.Success(question)
  }
}
