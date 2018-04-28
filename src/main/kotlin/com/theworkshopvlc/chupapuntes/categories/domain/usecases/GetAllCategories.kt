package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import org.springframework.stereotype.Component

@Component
class GetAllCategories(private val daoI: ICategoriesDAO) {
  fun execute() = daoI.findAll().toList()
}
