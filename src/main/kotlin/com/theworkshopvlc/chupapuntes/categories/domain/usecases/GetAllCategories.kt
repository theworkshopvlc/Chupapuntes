package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class GetAllCategories(private val dao: ICategoriesDAO) {
  fun execute(page: Int, pageSize: Int) =
    dao.findAll(PageRequest.of(page, pageSize))
}
