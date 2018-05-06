package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class GetAllQuestions(private val dao: IQuestionsDAO) {
  fun execute(page: Int, pageSize: Int) =
    dao.findAll(PageRequest.of(page, pageSize))
}
