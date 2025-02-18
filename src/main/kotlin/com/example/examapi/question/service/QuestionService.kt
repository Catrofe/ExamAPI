package com.example.examapi.question.service

import com.example.examapi.discipline.service.DisciplineService
import com.example.examapi.question.domain.Alternative
import com.example.examapi.question.domain.Question
import com.example.examapi.question.dto.RegistryNewQuestion
import com.example.examapi.question.port.QuestionRepository
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class QuestionService(
    val questionRepository: QuestionRepository,
    val disciplineService: DisciplineService
) {

    private val logger: Logger = LoggerFactory.getLogger(QuestionService::class.java)

    @Transactional
    suspend fun registryNewQuestion(newQuestion: RegistryNewQuestion): Question? = withContext(Dispatchers.IO) {
        logger.info("Creating new question")
        val disciplineDeferred = async { disciplineService.getById(newQuestion.disciplineId) }

        logger.info("Creating alternatives for question")
        val alternatives: List<Alternative> = newQuestion.alternatives.map {
            Alternative(
                description = it.description,
                correct = it.correct
            )
        }
        logger.info("Alternatives created: $alternatives")

        logger.info("Getting discipline for question")
        val discipline = disciplineDeferred.await()

        logger.info("Saving question")
        return@withContext questionRepository.save(
            Question(
                description = newQuestion.description,
                discipline = discipline,
                alternatives = alternatives.toMutableList(),
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