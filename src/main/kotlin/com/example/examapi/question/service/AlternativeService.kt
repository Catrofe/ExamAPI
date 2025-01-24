package com.example.examapi.question.service

import com.example.examapi.question.domain.Alternative
import com.example.examapi.question.dto.AlternativeRegistry
import com.example.examapi.question.port.AlternativeRepository
import org.springframework.stereotype.Service

@Service
class AlternativeService(val alternativeRepository: AlternativeRepository) {
    fun create(alternative: AlternativeRegistry): Alternative {
        return alternativeRepository.save(Alternative(
            description = alternative.description,
            correct = alternative.correct
        ))
    }
}