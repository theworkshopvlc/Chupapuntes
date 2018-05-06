package com.theworkshopvlc.chupapuntes.categories

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetAllCategories
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.SearchCategoriesByTitle
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
@Api(value = "categories", description = "Endpoints for Categories resource")
class CategoriesController(
  private val getAllCategories: GetAllCategories,
  private val searchCategoriesByTitle: SearchCategoriesByTitle
) {

  @GetMapping
  @ApiOperation(value = "List all Categories")
  fun index(): List<Category> =
    getAllCategories.execute()

  @GetMapping("/search")
  @ApiOperation(value = "Full text search of Categories by Title")
  fun searchByTitle(@RequestParam("title") title: String): List<Category> =
    searchCategoriesByTitle.execute(title)
}
