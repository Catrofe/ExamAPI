package com.example.examapi.question.adapter

import com.example.examapi.question.domain.Question
import com.example.examapi.question.dto.RegistryNewQuestion
import com.example.examapi.question.port.QuestionControllerPort
import com.example.examapi.question.service.QuestionService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
class QuestionControllerAdapter(val questionService: QuestionService) : QuestionControllerPort {

    override fun registryNewQuestion(@Validated @RequestBody
                                     newQuestion: RegistryNewQuestion): ResponseEntity<Question> {
        return ResponseEntity.ok(questionService.registryNewQuestion(newQuestion))
    }

    override fun getQuestionById(@PathVariable id: Long): ResponseEntity<Question> {
        return ResponseEntity.ok(questionService.getQuestionById(id))
    }

    override fun getQuestionByDisciplineId(@PathVariable disciplineId: Long): ResponseEntity<List<Question>> {
        return ResponseEntity.ok(questionService.getQuestionByDisciplineId(disciplineId))
    }

}