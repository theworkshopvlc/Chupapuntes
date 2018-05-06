package com.theworkshopvlc.chupapuntes.questions.model.usecases

import com.theworkshopvlc.chupapuntes.extensions.stripAccents
import com.theworkshopvlc.chupapuntes.extensions.toSnakeCase
import com.theworkshopvlc.chupapuntes.questions.persistence.IQuestionsDAO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class SearchQuestionsByTitle(private val dao: IQuestionsDAO) {
  fun execute(title: String, page: Int, pageSize: Int) =
    dao.searchByTitle(title.toLowerCase().toSnakeCase().stripAccents(), PageRequest.of(page, pageSize))
}
