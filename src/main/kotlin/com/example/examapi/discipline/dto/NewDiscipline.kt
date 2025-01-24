package com.example.examapi.discipline.dto

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull

data class NewDiscipline(
    @NotNull
    @NotBlank
    @Length(max = 155)
    val name: String,
    @NotNull
    @NotBlank
    @Length(max = 500)
    val description: String
)
