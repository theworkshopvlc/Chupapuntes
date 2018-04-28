package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.CategoriesDAO
import org.springframework.stereotype.Component

@Component
class GetAllCategories(private val dao: CategoriesDAO) {
  fun execute() = dao.findAll().toList()
}
