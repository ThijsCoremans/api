package com.mediagenix.api.persistence.repository

import com.mediagenix.api.persistence.entity.Collection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CollectionRepository: JpaRepository<Collection, Long>
