package com.example.examapi.exam.adapter

import com.example.examapi.exam.dto.ExamResponse
import com.example.examapi.exam.dto.NewExam
import com.example.examapi.exam.port.ExamControllerPort
import com.example.examapi.exam.service.ExamService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
class ExamControllerAdapter(
    val examService: ExamService,
): ExamControllerPort {
    @PostMapping
    override fun createNewExam(@RequestBody @Valid newExam: NewExam): ResponseEntity<ExamResponse> {
        return ResponseEntity.ok(examService.createNewExam(newExam))
    }
}