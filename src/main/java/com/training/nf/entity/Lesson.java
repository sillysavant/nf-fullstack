package com.training.nf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    Course course;

    @Column(name = "lesson_title", nullable = false)
    @NotNull(message = "Title is required!")
    @NotEmpty(message = "Title is required!")
    @Pattern(regexp = ".*\\S+.*", message = "Title is required!")
    @Size(max = 100)
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "video_url")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    String videoUrl;

    @OneToOne(mappedBy = "lesson")
    @JsonIgnore
    Quiz quiz;

    @Column(name = "date_created", nullable = false)
    LocalDate dateCreated;
}
