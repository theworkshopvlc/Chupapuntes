package com.theworkshopvlc.chupapuntes.categories.domain

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CategorySearchByTitleTest {
  var mockedDAO = Mockito.mock(ICategoriesDAO::class.java)

  @Test
  fun `if no exist categories with that text in the title should return empty list`() {
    `when`(mockedDAO.searchByTitle("Test")).thenReturn(emptyList())
    val resultList: List<Category> = mockedDAO.searchByTitle("Programming")

    assertThat(resultList.size, `is`(0))
  }

  @Test
  fun `if text is Maths should return 2 categories`() {
    `when`(mockedDAO.searchByTitle("Maths")).thenReturn(multipleResult())
    val resultList: List<Category> = mockedDAO.searchByTitle("Maths")

    assertThat(resultList.size, `is`(2))
  }

  @Test
  fun `if text is Simple should return 1 category`() {
    `when`(mockedDAO.searchByTitle("Simple")).thenReturn(singleReturn())

    val resultList: List<Category> = mockedDAO.searchByTitle("Simple")

    assertThat(resultList.size, `is`(1))
  }

  fun multipleResult(): List<Category> = listOf(Category(0, "Maths"), Category(0, "Simple Maths"))
  fun singleReturn(): List<Category> = listOf(Category(0, "Simple Maths"))
}
