package com.theworkshopvlc.chupapuntes.categories

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetAllCategories
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.SearchByTitle
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoriesController(
  private val getAllCategories: GetAllCategories,
  private val searchByTitle: SearchByTitle
) {

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER')")
  fun index(@RequestParam("title") title: String?): List<Category> = when {
    title != null -> searchByTitle.execute(title)
    else -> getAllCategories.execute()
  }
}
