package com.school.school.repository;

import com.school.school.model.ClassLevel;
import com.school.school.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassLevel, Integer> {
}
