package com.theworkshopvlc.chupapuntes.questions.persistence

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import org.springframework.data.repository.PagingAndSortingRepository

interface IQuestionsDAO : PagingAndSortingRepository<Question, Long>
