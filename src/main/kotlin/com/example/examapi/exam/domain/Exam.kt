package com.example.examapi.exam.domain

import com.example.examapi.exam.dto.NewExam
import com.example.examapi.question.domain.Question
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener::class)
data class Exam(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val studentName: String? = null,
    val studentEmail: String? = null,
    val studentRegistration: String? = null,
    val disciplineId: Long? = null,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "exam_questions",
        joinColumns = [JoinColumn(name = "exam_id")],
        inverseJoinColumns = [JoinColumn(name = "question_id")]
    )
    val questions: MutableList<Question>? = null,
    val points: Int = 10,
    val startTime: LocalDateTime? = null,
) {
    constructor(exam: NewExam, questions: MutableList<Question>) : this(
        studentName = exam.studentName,
        studentEmail = exam.studentEmail,
        studentRegistration = exam.studentRegistration,
        disciplineId = exam.disciplineId,
        questions = questions,
        startTime = LocalDateTime.now()
    )
}