package com.theworkshopvlc.chupapuntes.categories.domain

import com.theworkshopvlc.chupapuntes.categories.persistence.ICategoriesDAO

import org.junit.Test
import org.mockito.Mockito

class CategoryGetByTitleTest {
  var mockedDAO = Mockito.mock(ICategoriesDAO::class.java)

  @Test
  fun `if no exist categories with that text in the title should return empty list`() {
    `when`(mockedDAO.findByTitle("Test")).thenReturn(emptyList())
    val resultList: List<Category> = mockedDAO.findByTitle("Programming")

    assertThat(resultList.length).isEqualsTo(0)
  }

  @Test
  fun `if text is Maths should return 2 categories`() {
    `when`(mockedDAO.findByTitle("Maths")).thenReturn(multipleResult())
    val resultList: List<Category> = mockedDAO.findByTitle("Maths")

    assertThat(resultList.length).isEqualsTo(2)
  }

  @Test
  fun `if text is Simple should return 1 category`() {
    `when`(mockedDAO.findByTitle("Simple")).thenReturn(singleReturn())

    val resultList: List<Category> = mockedDAO.findByTitle("Simple")

    assertThat(resultList.length).isEqualsTo(1)
  }

  fun multipleResult(): List<Category> = listOf(Category(0, "Maths"), Category(0, "Simple Maths"))
  fun singleReturn(): List<Category> = listOf(Category(0, "Simple Maths"))

}
