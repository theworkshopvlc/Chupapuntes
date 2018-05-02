package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO

class GetByTitle(private val dao: ICategoriesDAO) {
  fun execute(query: String) = dao.findByTitle(query)
}
