package com.example.examapi.question.port

import com.example.examapi.question.domain.Question
import com.example.examapi.question.dto.RegistryNewQuestion
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

interface QuestionControllerPort {
    @PostMapping
    fun registryNewQuestion(newQuestion: RegistryNewQuestion): ResponseEntity<Question>

    @GetMapping("/{id}")
    fun getQuestionById(@PathVariable id: Long): ResponseEntity<Question>

    @GetMapping("/discipline/{disciplineId}")
    fun getQuestionByDisciplineId(@PathVariable disciplineId: Long): ResponseEntity<List<Question>>
}