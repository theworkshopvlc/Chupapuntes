package com.theworkshopvlc.chupapuntes.categories.domain.usecases

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import com.theworkshopvlc.chupapuntes.extensions.stripAccents
import com.theworkshopvlc.chupapuntes.extensions.toSnakeCase
import org.springframework.stereotype.Component

@Component
class SearchByTitle(private val dao: ICategoriesDAO) {
  fun execute(query: String) = dao.searchByTitle(query.toLowerCase().toSnakeCase().stripAccents())
}
