package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, String> {
}
