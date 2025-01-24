package com.example.examapi.exam.dto

data class NewExam(
    val studentName: String,
    val studentEmail: String,
    val studentRegistration: String,
    val disciplineId: Long
)
