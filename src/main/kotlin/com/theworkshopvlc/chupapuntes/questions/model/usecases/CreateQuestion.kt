package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationsResults
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

  fun execute(question: QuestionRequest, principal: Principal): QuestionValidationsResults {
    val questionEntity = question.toEntity()
    val validationResult = validateQuestion(questionEntity)

    if (validationResult is QuestionValidationsResults.Success) {
      val categories = question.categories.map { categoryDao.findBySearchTitle(it) }

      if (categories.anyIsNull())
        return QuestionValidationsResults.CategoryNotFoundError()

      val questionEntityWithCategories = validationResult.question
        .copy(
          categories = categories.filterNotNull().toSet(),
          author = userDao.findByUsername(principal.name)!!
        )
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
