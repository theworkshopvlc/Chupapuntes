package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationResults
import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import com.theworkshopvlc.chupapuntes.extensions.anyIsNull
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionRequest
import com.theworkshopvlc.chupapuntes.questions.model.entities.toEntity
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import com.theworkshopvlc.chupapuntes.users.persistence.IUserDAO
import org.springframework.stereotype.Component
import java.security.Principal

@Component
class CreateQuestion(
  private val dao: IQuestionsDAO,
  private val categoryDao: ICategoriesDAO,
  private val userDao: IUserDAO
) {

  fun execute(question: QuestionRequest, principal: Principal): QuestionValidationResults {
    val questionEntity = question.toEntity()
    val validationResult = validateQuestion(questionEntity)

    if (validationResult is QuestionValidationResults.Success) {
      val categories = question.categories.map { categoryDao.findBySearchTitle(it) }

      if (categories.anyIsNull())
        return QuestionValidationResults.CategoryNotFoundError

      val questionEntityWithCategories = validationResult.question
        .copy(
          categories = categories.filterNotNull().toSet(),
          author = userDao.findByUsername(principal.name)!!
        )
      dao.save(questionEntityWithCategories)

      return QuestionValidationResults.Success(questionEntityWithCategories)
    }

    return validationResult
  }

  private fun validateQuestion(question: Question): QuestionValidationResults = when {
    question.title.isEmpty() -> QuestionValidationResults.EmptyTitleError
    question.description.isEmpty() -> QuestionValidationResults.EmptyDescriptionError
    question.title.length > Question.MAX_TITLE_LENGTH -> QuestionValidationResults.BigTitleError
    else -> QuestionValidationResults.Success(question)
  }
}
