package com.example.examapi.question.domain

import com.example.examapi.discipline.domain.Discipline
import jakarta.persistence.*

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener::class)
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var description: String? = null,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var typeQuestion: TypeQuestion? =  TypeQuestion.MULTIPLE_CHOICE,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var questionLevel: QuestionLevel? = null,
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    var discipline: Discipline? = null,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "alternative_id")
    var alternatives: MutableList<Alternative> = mutableListOf()
)
