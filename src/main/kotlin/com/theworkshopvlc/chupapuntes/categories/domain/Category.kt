package com.theworkshopvlc.chupapuntes.categories.domain

import com.theworkshopvlc.chupapuntes.extensions.stripAccents
import com.theworkshopvlc.chupapuntes.extensions.toSnakeCase
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Categories")
data class Category(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,
  val title: String = "",
  val searchTitle: String = title.toLowerCase().toSnakeCase().stripAccents()
)
