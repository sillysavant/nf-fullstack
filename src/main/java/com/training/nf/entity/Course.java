package com.training.nf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "course_name", nullable = false, length = 100)
    @NotNull(message = "Name is required!")
    @NotEmpty(message = "Name is required!")
    @Pattern(regexp = ".*\\S+.*", message = "Name is required!")
    @Size(min = 2, message = "Name should have at least 2 words and at most 100 words.")
    String name;

    @Column(name = "difficulty", nullable = false)
    @NotNull(message = "Difficulty is required!")
    @NotEmpty(message = "Difficulty is required!")
    Level difficulty;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    User author;

    @Column(name = "date_created", nullable = false)
    LocalDate createdDate;

    @Column(name = "description")
    String description;
}
