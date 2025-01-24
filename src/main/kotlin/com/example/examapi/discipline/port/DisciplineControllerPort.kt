package com.example.examapi.discipline.port

import com.example.examapi.discipline.domain.Discipline
import com.example.examapi.discipline.dto.NewDiscipline
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


interface DisciplineControllerPort {

    @PostMapping
    fun create(@RequestBody discipline: NewDiscipline): ResponseEntity<Discipline>

    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "0") page: Int,
    ): ResponseEntity<Page<Discipline>>

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Discipline>

    @GetMapping("/search")
    fun search(
        @RequestParam name: String,
        @RequestParam(defaultValue = "0") page: Int
        ): ResponseEntity<Page<Discipline>>
}