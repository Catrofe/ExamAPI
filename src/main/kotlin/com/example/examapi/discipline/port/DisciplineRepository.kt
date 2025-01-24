package com.example.examapi.discipline.port

import com.example.examapi.discipline.domain.Discipline
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DisciplineRepository : JpaRepository<Discipline, Long> {

    fun findByNameContaining(name: String, pageable: Pageable): Page<Discipline>
}