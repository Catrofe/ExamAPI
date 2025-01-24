package com.example.examapi.discipline.service

import com.example.examapi.discipline.domain.Discipline
import com.example.examapi.discipline.dto.NewDiscipline
import com.example.examapi.discipline.port.DisciplineRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DisciplineService(val disciplineRepository: DisciplineRepository) {

    fun create(newDiscipline: NewDiscipline) =
        disciplineRepository.save(
            Discipline(
                name = newDiscipline.name,
                description = newDiscipline.description
            )
    )

    fun getAll(pageable: Pageable): Page<Discipline>? =
        disciplineRepository.findAll(pageable)


    fun getById(id: Long): Discipline? =
        disciplineRepository.findById(id).orElse(null)


    fun search(name: String, pageable: Pageable): Page<Discipline>? =
        disciplineRepository.findByNameContaining(name, pageable)
}
