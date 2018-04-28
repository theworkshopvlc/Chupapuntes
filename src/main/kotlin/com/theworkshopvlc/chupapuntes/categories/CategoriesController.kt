package com.theworkshopvlc.chupapuntes.categories

import com.theworkshopvlc.chupapuntes.categories.domain.Category
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetAllCategories
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoriesController(private val getAllCategories: GetAllCategories) {

  @GetMapping
  fun getAll(): List<Category> {
    return getAllCategories.execute()
  }

}
