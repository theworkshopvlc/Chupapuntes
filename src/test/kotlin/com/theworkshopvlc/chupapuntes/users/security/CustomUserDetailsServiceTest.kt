package com.theworkshopvlc.chupapuntes.users.security

import com.theworkshopvlc.chupapuntes.users.model.entities.User
import com.theworkshopvlc.chupapuntes.users.model.errors.UserNotFound
import com.theworkshopvlc.chupapuntes.users.model.usecases.GetUserByUsername
import com.theworkshopvlc.chupapuntes.users.persistence.IUserDAO
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CustomUserDetailsServiceTest {
  lateinit var service: CustomUserDetailsService
  var mockedDAO = mock(IUserDAO::class.java)

  @Before
  fun setup() {
    service = CustomUserDetailsService(GetUserByUsername(mockedDAO))
  }

  @Test(expected = UserNotFound::class)
  fun `if user does not exist exception should be thrown`() {
    `when`(mockedDAO.findByUsername(ArgumentMatchers.anyString())).thenReturn(null)

    service.loadUserByUsername("Juan")
  }

  @Test
  fun `if user exists then service should return it`() {
    `when`(mockedDAO.findByUsername(ArgumentMatchers.anyString())).thenReturn(fakeUser())

    val user = service.loadUserByUsername("Juan")

    assertThat(user.username, `is`("Juan"))
  }

  fun fakeUser() = User(0, "Juan", "123")
}
