package com.training.nf.service;

import com.training.nf.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(Course dto);
    List<Course> getAll(Integer limit, Integer offset, Boolean isActive, String keyword);
    Course update(Long id, Course dto);
    void delete(Long id);
    Course findById(Long id);
}
