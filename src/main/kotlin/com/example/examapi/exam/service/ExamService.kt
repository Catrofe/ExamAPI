package com.example.examapi.exam.service

import com.example.examapi.exam.domain.Exam
import com.example.examapi.exam.dto.ExamResponse
import com.example.examapi.exam.dto.NewExam
import com.example.examapi.exam.port.ExamRepository
import com.example.examapi.question.domain.Question
import com.example.examapi.question.domain.QuestionLevel
import com.example.examapi.question.port.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExamService(
    val examRepository: ExamRepository,
    val questionRepository: QuestionRepository,
) {
    fun createNewExam(newExam: NewExam): ExamResponse {
        val questions = questionRepository.findByDisciplineId(newExam.disciplineId)
        val (hardQuestions, mediumQuestions, easyQuestions) = separateQuestionsByDifficulty(questions)
        val examQuestions = (hardQuestions + mediumQuestions + easyQuestions).toMutableList()
        val exam = saveExam(newExam, examQuestions)
        exam.questions?.shuffle()
        exam.questions?.forEach { shuffleAlternativesInQuestion(it) }
        return ExamResponse(exam)
    }

    @Transactional
    fun saveExam(newExam: NewExam, examQuestion: MutableList<Question>): Exam {
        val exam = Exam(newExam, examQuestion)
        return examRepository.saveAndFlush(examRepository.save(exam))
    }

    private fun shuffleAlternativesInQuestion(question: Question) {
        question.alternatives = question.alternatives.shuffled().toMutableList()
    }

    private fun separateQuestionsByDifficulty(questions: List<Question>):
            Triple<List<Question>, List<Question>, List<Question>> {
        val (hardQuestions, mediumQuestions, easyQuestions) =
            Triple(mutableListOf<Question>(), mutableListOf<Question>(), mutableListOf<Question>())
        for (question in questions) {
            when (question.questionLevel) {
                QuestionLevel.HARD -> hardQuestions.add(question)
                QuestionLevel.MEDIUM-> mediumQuestions.add(question)
                QuestionLevel.EASY -> easyQuestions.add(question)
                null -> TODO()
            }
        }
        return Triple(hardQuestions, mediumQuestions, easyQuestions)
    }
}