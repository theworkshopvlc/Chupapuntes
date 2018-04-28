package com.theworkshopvlc.chupapuntes.votes.domain

import javax.persistence.*

@Entity
@Table(name = "Votes")
data class Votes(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  val positive: Boolean = false,

  val createdAt: Long = System.currentTimeMillis(),

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  val user: User? = null,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "answer_id")
  val answer: Answer? = null
)
