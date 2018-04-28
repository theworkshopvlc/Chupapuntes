package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO

class GetByTitle(private val daoI: ICategoriesDAO) {
  fun execute(query: String) = daoI.findByTitle(query)
}
