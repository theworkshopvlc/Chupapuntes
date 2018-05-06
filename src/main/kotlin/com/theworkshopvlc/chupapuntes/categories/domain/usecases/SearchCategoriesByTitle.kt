package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import com.theworkshopvlc.chupapuntes.extensions.stripAccents
import com.theworkshopvlc.chupapuntes.extensions.toSnakeCase
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class SearchCategoriesByTitle(private val dao: ICategoriesDAO) {
  fun execute(query: String, page: Int, pageSize: Int) =
    dao.searchByTitle(query.toLowerCase().toSnakeCase().stripAccents(), PageRequest.of(page, pageSize))
}
