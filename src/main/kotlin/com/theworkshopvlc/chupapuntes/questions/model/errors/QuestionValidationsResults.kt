package com.theworkshopvlc.chupapuntes.questions.model.errors

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question

sealed class QuestionValidationsResults(val error: String) {

  companion object {
    private const val EMPTY_TITLE_ERROR = "Title cannot be empty"
    private const val EMPTY_DESCRIPTION_ERROR = "Description cannot be empty"
    private const val BIG_TITLE_ERROR = "Title must be shorter than ${Question.MAX_TITLE_LENGTH} characters"
    private const val CATEGORY_NOT_FOUND = "Some category was not found"
  }

  class Success(val question: Question) : QuestionValidationsResults("")
  class EmptyTitleError(error: String = EMPTY_TITLE_ERROR) : QuestionValidationsResults(error)
  class EmptyDescriptionError(error: String = EMPTY_DESCRIPTION_ERROR) : QuestionValidationsResults(error)
  class BigTitleError(error: String = BIG_TITLE_ERROR) : QuestionValidationsResults(error)
  class CategoryNotFoundError(error: String = CATEGORY_NOT_FOUND) : QuestionValidationsResults(error)
}
