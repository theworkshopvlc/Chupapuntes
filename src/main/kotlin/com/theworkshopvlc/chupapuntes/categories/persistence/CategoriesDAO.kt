package com.theworkshopvlc.chupapuntes.categories.persistence

import com.theworkshopvlc.chupapuntes.categories.domain.entities.Category
import org.springframework.data.repository.CrudRepository

interface CategoriesDAO : CrudRepository<Category, Long>
