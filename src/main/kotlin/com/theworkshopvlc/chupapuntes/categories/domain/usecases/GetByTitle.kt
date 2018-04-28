package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.CategoriesDAO

class GetByTitle(private val dao: CategoriesDAO) {
  fun execute(query: String) = dao.findByTitle(query)
}
