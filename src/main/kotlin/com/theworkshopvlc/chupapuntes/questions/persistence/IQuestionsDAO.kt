package com.theworkshopvlc.chupapuntes.questions.persistence

import com.theworkshopvlc.chupapuntes.questions.model.entities.Question
import org.springframework.data.repository.CrudRepository

interface IQuestionsDAO : CrudRepository<Question, Long>
