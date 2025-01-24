package com.example.examapi.exam.port

import com.example.examapi.exam.domain.Exam
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository: JpaRepository<Exam, Long> {
}