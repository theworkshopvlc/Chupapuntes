package com.theworkshopvlc.chupapuntes.categories.persistence

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface ICategoriesDAO : PagingAndSortingRepository<Category, Long> {

  @Query("SELECT c FROM Category c WHERE c.searchTitle LIKE '%' || :textTitle || '%'")
  fun searchByTitle(textTitle: String, pageable: Pageable): Page<Category>

  fun findBySearchTitle(title: String): Category?
}
