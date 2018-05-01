package com.theworkshopvlc.chupapuntes.categories

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetAllCategories
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetCategoriesByTitle
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoriesController(private val getAllCategories: GetAllCategories,
                           private val getCategoriesByTitle: GetCategoriesByTitle) {

  @GetMapping("/index")
  @PreAuthorize("hasAnyAuthority('USER')")
  fun getAll(): List<Category> {
    return getAllCategories.execute()
  }

  @GetMapping("/getCategoriesByTitle")
  fun getByTitle(@RequestParam("title") query: String): List<Category> {
    return getCategoriesByTitle.execute(query)
  }

}
