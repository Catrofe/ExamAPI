package com.example.examapi.question.port

import com.example.examapi.question.domain.Alternative
import org.springframework.data.jpa.repository.JpaRepository

interface AlternativeRepository : JpaRepository<Alternative, Long> {
}