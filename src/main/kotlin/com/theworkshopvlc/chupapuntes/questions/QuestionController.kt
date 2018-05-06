package com.theworkshopvlc.chupapuntes.questions

import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationsResults
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionRequest
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionResponse
import com.theworkshopvlc.chupapuntes.questions.model.entities.toResponse
import com.theworkshopvlc.chupapuntes.questions.model.usecases.CreateQuestion
import com.theworkshopvlc.chupapuntes.questions.model.usecases.GetAllQuestions
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/questions")
class QuestionController(private val getAllQuestions: GetAllQuestions,
                         private val createQuestion: CreateQuestion) {

  @GetMapping
  fun index(): List<QuestionResponse> =
    getAllQuestions.execute()
      .map(Question::toResponse)

  @PostMapping
  fun newQuestion(@RequestBody question: QuestionRequest): ResponseEntity<*> {
    val result = createQuestion.execute(question)
    return when (result) {
      is QuestionValidationsResults.Success -> ResponseEntity
        .status(HttpStatus.CREATED)
        .body(result.question.toResponse())

      else -> ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(result.error)
    }
  }
}
