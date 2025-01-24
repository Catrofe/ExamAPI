package com.example.examapi.exam.dto

import com.example.examapi.question.domain.Question

data class QuestionResponse(
    val id: Long,
    val description: String,
    val typeQuestion: String,
    val alternatives: List<AlternativeResponse>
){
    constructor(question: Question): this(
        id = question.id!!,
        description = question.description!!,
        typeQuestion = question.typeQuestion!!.toString(),
        alternatives = question.alternatives!!.map { AlternativeResponse(it) }
    )
}
