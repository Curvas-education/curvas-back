package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Page<Student> findAllByActiveTrue(Pageable page);
}
