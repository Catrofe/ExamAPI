package com.example.examapi.question.domain

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener::class)
data class Alternative(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var description: String? = null,
    var correct: Boolean? = null
)
