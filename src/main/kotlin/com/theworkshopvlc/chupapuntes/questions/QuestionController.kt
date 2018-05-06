package com.theworkshopvlc.chupapuntes.questions

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.usecases.GetAllQuestions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/questions")
class QuestionController(private val getAllQuestions: GetAllQuestions) {

  @GetMapping
  fun getAll(): List<Question> =
    getAllQuestions.execute()
}
