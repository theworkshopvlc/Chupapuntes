package com.theworkshopvlc.chupapuntes.categories.domain

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO

import org.junit.Test
import org.mockito.Mockito

class GetCategoriesByTitleTest {
  var mockedDAO = Mockito.mock(ICategoriesDAO::class.java)

  @Test
  fun `if no exist categories with that text in the title should return empty list`() {
    Mockito.`when`(mockedDAO.findByTitle("Test")).thenReturn(emptyList())
  }

  @Test
  fun `if text is Maths should return 2 categories`() {
    Mockito.`when`(mockedDAO.findByTitle("Maths")).thenReturn(multipleResult())
  }

  @Test
  fun `if text is Simple should return 1 category`() {
    Mockito.`when`(mockedDAO.findByTitle("Simple")).thenReturn(singleReturn())
  }

  fun multipleResult(): List<Category> =
    listOf(
      Category(0, emptySet(), "Maths"),
      Category(0, emptySet(), "Simple Maths")
    )

  fun singleReturn(): List<Category> =
    listOf(
      Category(0, emptySet(), "Simple Maths")
    )
}
