package com.theworkshopvlc.chupapuntes.questions

import com.theworkshopvlc.chupapuntes.categories.domain.errors.QuestionValidationsResults
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
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
  fun index(): List<Question> =
    getAllQuestions.execute()

  @PostMapping
  fun newQuestion(@RequestBody question: Question): ResponseEntity<*> {
    val result = createQuestion.execute(question)
    return when (result) {
      is QuestionValidationsResults.Success -> ResponseEntity.status(HttpStatus.CREATED).body(result.question)
      else -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.error)
    }
  }
}
