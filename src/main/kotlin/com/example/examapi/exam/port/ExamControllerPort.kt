package com.example.examapi.exam.port

import com.example.examapi.exam.dto.ExamResponse
import com.example.examapi.exam.dto.NewExam
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface ExamControllerPort {
    @PostMapping
    fun createNewExam(@RequestBody @Valid newExam: NewExam): ResponseEntity<ExamResponse>
}