package com.theworkshopvlc.chupapuntes.questions

import com.theworkshopvlc.chupapuntes.questions.model.errors.QuestionValidationResults
import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionRequest
import com.theworkshopvlc.chupapuntes.questions.model.entities.QuestionResponse
import com.theworkshopvlc.chupapuntes.questions.model.entities.toResponse
import com.theworkshopvlc.chupapuntes.questions.model.usecases.CreateQuestion
import com.theworkshopvlc.chupapuntes.questions.model.usecases.GetAllQuestions
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/questions")
@Api(value = "questions", description = "Endpoints for Questions resource")
class QuestionController(
  private val getAllQuestions: GetAllQuestions,
  private val createQuestion: CreateQuestion
) {

  @GetMapping
  @ApiOperation(value = "List all Questions")
  fun index(@RequestParam("page") page: Int,
            @RequestParam("page_size") pageSize: Int?): Page<QuestionResponse> =
    getAllQuestions.execute(page, pageSize ?: 10)
      .map(Question::toResponse)

  @PostMapping
  @ApiOperation(value = "Create a new Question")
  @ApiResponses(value = [
    ApiResponse(code = 201, message = "Created correctly", response = QuestionResponse::class),
    ApiResponse(code = 400, message = "Error responses can be found in `QuestionValidationResults`")
  ])
  fun newQuestion(
    @RequestBody question: QuestionRequest,
    principal: Principal
  ): ResponseEntity<*> {
    val result = createQuestion.execute(question, principal)
    return when (result) {
      is QuestionValidationResults.Success -> ResponseEntity
        .status(HttpStatus.CREATED)
        .body(result.question.toResponse())

      else -> ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(result.error)
    }
  }
}
