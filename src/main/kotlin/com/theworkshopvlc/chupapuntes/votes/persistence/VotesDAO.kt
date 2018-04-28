package com.theworkshopvlc.chupapuntes.votes.persistence

import com.theworkshopvlc.chupapuntes.votes.domain.Votes
import org.springframework.data.repository.CrudRepository


interface VotesDAO: CrudRepository<Vote, Long>
