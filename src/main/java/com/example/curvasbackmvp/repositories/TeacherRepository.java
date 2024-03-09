package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Page<Teacher> findAllByActiveTrue(Pageable page);
}
