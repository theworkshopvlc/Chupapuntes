package com.theworkshopvlc.chupapuntes.categories.persistence

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ICategoriesDAO : CrudRepository<Category, Long> {

  @Query("SELECT c FROM Category c WHERE c.searchTitle LIKE '%' || :textTitle || '%'")
  fun searchByTitle(textTitle: String): List<Category>

  fun findBySearchTitle(title: String): Category?
}
