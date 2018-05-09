package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.entities.CategoryRequest
import com.theworkshopvlc.chupapuntes.categories.domain.errors.CategoryValidationResults
import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.should
import io.kotlintest.specs.StringSpec
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CreateCategoryTest : StringSpec() {

  private val mockDAO = Mockito.mock(ICategoriesDAO::class.java)
  private val createCategory = CreateCategory(mockDAO)

  init {

    "Category with empty title should return validation error" {
      val category = CategoryRequest("")

      val result = createCategory.execute(category)

      result should beOfType<CategoryValidationResults.EmptyTitleError>()
    }

    "Category with title bigger than ${Category.MAX_TITLE_LENGTH} should return validation error" {
      val category = CategoryRequest("A".repeat(Category.MAX_TITLE_LENGTH + 1))

      val result = createCategory.execute(category)

      result should beOfType<CategoryValidationResults.BigTitleError>()
    }

    "If a Category with some title already exists it should return a validation error" {
      `when`(mockDAO.findBySearchTitle(ArgumentMatchers.anyString())).thenReturn(Category())

      val category = CategoryRequest("Maths")

      val result = createCategory.execute(category)

      result should beOfType<CategoryValidationResults.AlreadyExists>()
    }

    "If no Category with a title already exists it should return success" {
      `when`(mockDAO.findBySearchTitle(ArgumentMatchers.anyString())).thenReturn(null)
      `when`(mockDAO.save(ArgumentMatchers.any(Category::class.java))).thenReturn(Category())

      val category = CategoryRequest("Maths")

      val result = createCategory.execute(category)

      result should beOfType<CategoryValidationResults.Success>()
    }
  }
}
