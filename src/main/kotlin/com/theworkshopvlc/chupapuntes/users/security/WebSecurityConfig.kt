package com.theworkshopvlc.chupapuntes.users.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
  private val userDetailsService: UserDetailsService,
  private val tokenHelper: TokenHelper
) : WebSecurityConfigurerAdapter() {

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder())
  }

  override fun configure(http: HttpSecurity) {
    http
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .cors().and()
      .csrf().disable()
      .authorizeRequests().antMatchers("/auth/**").permitAll()
      .anyRequest().authenticated().and()
      .addFilterBefore(TokenAuthenticationFilter(tokenHelper, userDetailsService),
        UsernamePasswordAuthenticationFilter::class.java)
  }

}