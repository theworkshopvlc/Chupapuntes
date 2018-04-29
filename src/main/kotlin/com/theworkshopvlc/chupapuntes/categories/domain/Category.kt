package com.theworkshopvlc.chupapuntes.categories.domain

import javax.persistence.*

@Entity
@Table(name = "Categories")
data class Category(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,
  val title: String = "Hola"
)
