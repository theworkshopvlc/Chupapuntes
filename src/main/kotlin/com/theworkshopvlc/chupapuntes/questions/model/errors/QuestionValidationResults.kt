package com.theworkshopvlc.chupapuntes.questions.model.errors

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question

sealed class QuestionValidationResults(val error: String) {

  companion object {
    private const val EMPTY_TITLE_ERROR = "Title cannot be empty"
    private const val EMPTY_DESCRIPTION_ERROR = "Description cannot be empty"
    private const val BIG_TITLE_ERROR = "Title must be shorter than ${Question.MAX_TITLE_LENGTH} characters"
    private const val CATEGORY_NOT_FOUND = "Some category was not found"
  }

  class Success(val question: Question) : QuestionValidationResults("")
  object EmptyTitleError : QuestionValidationResults(EMPTY_TITLE_ERROR)
  object EmptyDescriptionError : QuestionValidationResults(EMPTY_DESCRIPTION_ERROR)
  object BigTitleError : QuestionValidationResults(BIG_TITLE_ERROR)
  object CategoryNotFoundError : QuestionValidationResults(CATEGORY_NOT_FOUND)
}
