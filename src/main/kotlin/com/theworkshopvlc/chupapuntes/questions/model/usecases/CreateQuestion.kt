package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationsResults
import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import com.theworkshopvlc.chupapuntes.extensions.anyIsNull
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionRequest
import com.theworkshopvlc.chupapuntes.questions.model.entities.toEntity
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import org.springframework.stereotype.Component

@Component
class CreateQuestion(private val dao: IQuestionsDAO,
                     private val categoryDao: ICategoriesDAO) {

  fun execute(question: QuestionRequest): QuestionValidationsResults {
    val questionEntity = question.toEntity()
    val validationResult = validateQuestion(questionEntity)

    if (validationResult is QuestionValidationsResults.Success) {
      val categories = question.categories.map { categoryDao.findBySearchTitle(it) }

      if (categories.anyIsNull())
        return QuestionValidationsResults.CategoryNotFoundError()

      val questionEntityWithCategories = validationResult.question.copy(categories = categories.filterNotNull().toSet())
      dao.save(questionEntityWithCategories)

      return QuestionValidationsResults.Success(questionEntityWithCategories)
    }

    return validationResult
  }

  private fun validateQuestion(question: Question): QuestionValidationsResults = when {
    question.title.isEmpty() -> QuestionValidationsResults.EmptyTitleError()
    question.description.isEmpty() -> QuestionValidationsResults.EmptyDescriptionError()
    question.title.length > Question.MAX_TITLE_LENGTH -> QuestionValidationsResults.BigTitleError()
    else -> QuestionValidationsResults.Success(question)
  }
}
