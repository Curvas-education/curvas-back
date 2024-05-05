package com.example.curvasbackmvp.repositories;


import com.example.curvasbackmvp.models.exam.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, String> {
}
