package com.training.nf.service;

import com.training.nf.dto.LessonRequestDto;
import com.training.nf.entity.Lesson;

public interface LessonService {
    Lesson findById(Long id);
    Lesson create(Lesson lesson, LessonRequestDto dto);
    Lesson update(Long courseId, Long lessonId, LessonRequestDto dto);
    void delete(Long courseId, Long lessonId);
}
