package com.example.examapi.exam.dto

import com.example.examapi.question.domain.Alternative

data class AlternativeResponse(
    val id: Long,
    val description: String
){
    constructor(alternative: Alternative): this(
        id = alternative.id!!,
        description = alternative.description!!
    )
}
