package com.example.examapi.discipline.adapter

import com.example.examapi.discipline.domain.Discipline
import com.example.examapi.discipline.dto.NewDiscipline
import org.springframework.data.domain.Page
import com.example.examapi.discipline.port.DisciplineControllerPort
import org.springframework.http.ResponseEntity
import com.example.examapi.discipline.service.DisciplineService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/discipline")
@RequiredArgsConstructor
class DisciplineControllerAdapter(val disciplineService: DisciplineService) : DisciplineControllerPort {

    override fun create(@RequestBody @Valid discipline: NewDiscipline): ResponseEntity<Discipline> =
       ResponseEntity(disciplineService.create(discipline), HttpStatus.CREATED)

    override fun getAll(
        @RequestParam(defaultValue = "0") page: Int,
    ): ResponseEntity<Page<Discipline>> {
        val pageSize = 10
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return ResponseEntity.ok(disciplineService.getAll(pageable))
    }

    override fun getById(@PathVariable id: Long): ResponseEntity<Discipline> =
        disciplineService.getById(id)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    override fun search(
        @RequestParam name: String,
        @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<Page<Discipline>> {
        val pageSize = 10
        val pageable: Pageable = PageRequest.of(page, pageSize)
        val disciplinePage: Page<Discipline>? = disciplineService.search(name, pageable)
        return ResponseEntity.ok(disciplinePage)
}


}