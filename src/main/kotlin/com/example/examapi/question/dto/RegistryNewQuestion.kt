package com.example.examapi.question.dto

import com.example.examapi.question.domain.QuestionLevel
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class RegistryNewQuestion(
    @NotNull
    @NotBlank
    val description: String,
    @NotNull
    val disciplineId: Long,
    @NotNull
    val alternatives: List<AlternativeRegistry>,
    @NotNull
    val levelQuestion: QuestionLevel = QuestionLevel.EASY
)
