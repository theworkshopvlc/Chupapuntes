package com.theworkshopvlc.chupapuntes.categories.domain.entities

fun Category.toResponse(): CategoryResponse =
  CategoryResponse(this.id, this.title)
