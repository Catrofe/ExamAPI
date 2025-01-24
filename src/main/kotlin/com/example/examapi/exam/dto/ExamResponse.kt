package com.example.examapi.exam.dto

import com.example.examapi.exam.domain.Exam
import jakarta.persistence.*
import java.time.LocalDateTime

data class ExamResponse(
    val id: Long,
    val studentName: String,
    val disciplineId: Int,
    val questions: List<QuestionResponse>,
    val startTime: LocalDateTime,
){
    constructor(exam: Exam): this(
        id = exam.id!!,
        studentName = exam.studentName!!,
        disciplineId = exam.disciplineId!!.toInt(),
        questions = exam.questions!!.map { QuestionResponse(it) },
        startTime = exam.startTime!!
    )
}
