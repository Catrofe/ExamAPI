package com.example.examapi.question.port

import com.example.examapi.question.domain.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
    fun findByDisciplineId(disciplineId: Long): List<Question>
}