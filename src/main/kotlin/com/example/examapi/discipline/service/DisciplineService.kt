package com.example.examapi.discipline.service

import com.example.examapi.discipline.domain.Discipline
import com.example.examapi.discipline.dto.NewDiscipline
import com.example.examapi.discipline.port.DisciplineRepository
import com.example.examapi.question.service.QuestionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DisciplineService(val disciplineRepository: DisciplineRepository) {

    private val logger: Logger = LoggerFactory.getLogger(DisciplineService::class.java)

    fun create(newDiscipline: NewDiscipline) =
        disciplineRepository.save(
            Discipline(
                name = newDiscipline.name,
                description = newDiscipline.description
            )
    )

    fun getAll(pageable: Pageable): Page<Discipline>? =
        disciplineRepository.findAll(pageable)


    fun getById(id: Long): Discipline? {
        logger.info("Getting discipline by id: $id")
        val discipline = disciplineRepository.findById(id).orElse(null)
        logger.info("Discipline found: $discipline")
        return discipline
        }


    fun search(name: String, pageable: Pageable): Page<Discipline>? =
        disciplineRepository.findByNameContaining(name, pageable)
}
