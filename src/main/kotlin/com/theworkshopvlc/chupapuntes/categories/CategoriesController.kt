package com.theworkshopvlc.chupapuntes.categories

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.domain.entities.CategoryRequest
import com.theworkshopvlc.chupapuntes.categories.domain.entities.CategoryResponse
import com.theworkshopvlc.chupapuntes.categories.domain.entities.toResponse
import com.theworkshopvlc.chupapuntes.categories.domain.errors.CategoryValidationResults
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.CreateCategory
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.GetAllCategories
import com.theworkshopvlc.chupapuntes.categories.domain.usecases.SearchCategoriesByTitle
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
@Api(value = "categories", description = "Endpoints for Categories resource")
class CategoriesController(
  private val getAllCategories: GetAllCategories,
  private val searchCategoriesByTitle: SearchCategoriesByTitle,
  private val createCategory: CreateCategory
) {

  @GetMapping
  @ApiOperation(value = "List all Categories")
  fun index(
    @RequestParam("page") page: Int,
    @RequestParam("page_size") pageSize: Int?
  ): Page<CategoryResponse> =
    getAllCategories.execute(page, pageSize ?: 10)
      .map(Category::toResponse)

  @GetMapping("/search")
  @ApiOperation(value = "Full text search of Categories by Title")
  fun searchByTitle(
    @RequestParam("title") title: String,
    @RequestParam("page") page: Int,
    @RequestParam("page_size") pageSize: Int?
  ): Page<CategoryResponse> =
    searchCategoriesByTitle.execute(title, page, pageSize ?: 10)
      .map(Category::toResponse)

  @PostMapping
  @ApiOperation(value = "Create new Category")
  @io.swagger.annotations.ApiResponses(value = [
    ApiResponse(code = 201, message = "Created correctly", response = CategoryResponse::class),
    ApiResponse(code = 400, message = "Error responses can be found in `CategoryValidationsResults`")
  ])
  fun createNewCategory(@RequestBody categoryRequest: CategoryRequest): ResponseEntity<*> {
    val result = createCategory.execute(categoryRequest)

    return when (result) {
      is CategoryValidationResults.Success -> ResponseEntity
        .status(HttpStatus.CREATED)
        .body(result.category.toResponse())

      else -> ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(result.error)
    }
  }
}
