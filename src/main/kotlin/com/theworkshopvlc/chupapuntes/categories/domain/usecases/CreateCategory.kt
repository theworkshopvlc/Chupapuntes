package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.entities.CategoryRequest
import com.theworkshopvlc.chupapuntes.categories.domain.errors.CategoryValidationResults
import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import org.springframework.stereotype.Component

@Component
class CreateCategory(private val dao: ICategoriesDAO) {
  fun execute(categoryReq: CategoryRequest): CategoryValidationResults {
    val category = Category(title = categoryReq.title)
    val validationResult = validateCategory(category)

    if (validationResult is CategoryValidationResults.Success) {
      val isUnique = dao.findBySearchTitle(category.searchTitle) == null

      return when {
        isUnique -> CategoryValidationResults.Success(dao.save(category))
        else -> CategoryValidationResults.AlreadyExists
      }
    }

    return validationResult
  }

  private fun validateCategory(category: Category): CategoryValidationResults = when {
    category.title.isEmpty() -> CategoryValidationResults.EmptyTitleError
    category.title.length > Category.MAX_TITLE_LENGTH -> CategoryValidationResults.BigTitleError
    else -> CategoryValidationResults.Success(category)
  }
}
