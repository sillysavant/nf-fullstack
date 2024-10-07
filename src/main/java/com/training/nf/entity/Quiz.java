package com.training.nf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "lesson_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    Lesson lesson;

    @Column(name = "quiz_title", nullable = false)
    @NotNull(message = "Title is required!")
    @NotEmpty(message = "Title is required!")
    @Pattern(regexp = ".*\\S+.*", message = "Title is required!")
    String title;

    @Column(name = "total_questions", nullable = false)
    @Size(min = 1)
    int totalQuestions;

    @Column(name = "passing_score", nullable = false)
    @Size(min = 50, max = 100)
    int passingScore;
}
