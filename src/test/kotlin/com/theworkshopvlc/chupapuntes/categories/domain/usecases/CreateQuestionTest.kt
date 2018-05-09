package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionRequest
import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationResults
import com.theworkshopvlc.chupapuntes.questions.model.usecases.CreateQuestion
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import com.theworkshopvlc.chupapuntes.users.model.entities.User
import com.theworkshopvlc.chupapuntes.users.persistence.IUserDAO
import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.should
import io.kotlintest.specs.StringSpec
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CreateQuestionTest : StringSpec() {

  private val mockDAO = Mockito.mock(IQuestionsDAO::class.java)
  private val mockCategoryDAO = Mockito.mock(ICategoriesDAO::class.java)
  private val mockUserDAO = Mockito.mock(IUserDAO::class.java)
  private val createQuestion = CreateQuestion(mockDAO, mockCategoryDAO, mockUserDAO)

  init {
    "Question with empty title should return validation error" {
      val question = QuestionRequest("", "Test", emptySet())

      val result = createQuestion.execute(question, "Test")

      result should beOfType<QuestionValidationResults.EmptyTitleError>()
    }

    "Question with empty description should return validation error" {
      val question = QuestionRequest("Test", "", emptySet())

      val result = createQuestion.execute(question, "Test")

      result should beOfType<QuestionValidationResults.EmptyDescriptionError>()
    }

    "Question with big title should return validation error" {
      val question = QuestionRequest("A".repeat(Question.MAX_TITLE_LENGTH + 1), "Test", emptySet())

      val result = createQuestion.execute(question, "Test")

      result should beOfType<QuestionValidationResults.BigTitleError>()
    }

    "If Question is valid it should return success" {
      `when`(mockUserDAO.findByUsername(anyString())).thenReturn(User())
      `when`(mockDAO.save(any(Question::class.java))).thenReturn(Question())

      val question = QuestionRequest("Hello", "World", emptySet())

      val result = createQuestion.execute(question, "Test")

      result should beOfType<QuestionValidationResults.Success>()
    }
  }
}
