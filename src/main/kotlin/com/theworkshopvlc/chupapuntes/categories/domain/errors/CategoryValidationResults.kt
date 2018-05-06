package com.theworkshopvlc.chupapuntes.categories.domain.errors

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category

sealed class CategoryValidationResults(val error: String) {
  companion object {
    private const val EMTPY_TITLE_ERROR = "Title cannot be empty"
    private const val BIG_TITLE_ERROR = "Title cannot be longer than ${Category.MAX_TITLE_LENGTH} characters"
    private const val ALREADY_EXISTS_ERROR = "A Category with that title already exists"
  }

  class Success(val category: Category) : CategoryValidationResults("")
  object EmptyTitleError : CategoryValidationResults(EMTPY_TITLE_ERROR)
  object BigTitleError : CategoryValidationResults(BIG_TITLE_ERROR)
  object AlreadyExists : CategoryValidationResults(ALREADY_EXISTS_ERROR)
}
