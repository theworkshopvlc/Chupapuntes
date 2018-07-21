package com.theworkshopvlc.chupapuntes.questions.persistence

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface IQuestionsDAO : PagingAndSortingRepository<Question, Long> {
  @Query("SELECT c FROM Question c WHERE c.searchTitle LIKE '%' || :textTitle || '%'")
  fun searchByTitle(textTitle: String, pageable: Pageable): Page<Question>
}
