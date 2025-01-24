package com.example.examapi.question.service

import com.example.examapi.discipline.service.DisciplineService
import com.example.examapi.question.domain.Alternative
import com.example.examapi.question.domain.Question
import com.example.examapi.question.dto.RegistryNewQuestion
import com.example.examapi.question.port.QuestionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QuestionService(
    val questionRepository: QuestionRepository,
    val alternativeService: AlternativeService,
    val disciplineService: DisciplineService
) {
    @Transactional
    fun registryNewQuestion(newQuestion: RegistryNewQuestion): Question? {
        val alternatives: MutableList<Alternative> =
            newQuestion.alternatives.map { alternativeService.create(it) }.toMutableList()
        val discipline = disciplineService.getById(newQuestion.disciplineId)
        return questionRepository.save(
            Question(
                description = newQuestion.description,
                discipline = discipline,
                alternatives = alternatives,
                questionLevel = newQuestion.levelQuestion
            )
        )
    }

    fun getQuestionById(id: Long): Question? {
        return questionRepository.findById(id).orElse(null)
    }

    fun getQuestionByDisciplineId(disciplineId: Long): List<Question> {
        return questionRepository.findByDisciplineId(disciplineId)
    }
}