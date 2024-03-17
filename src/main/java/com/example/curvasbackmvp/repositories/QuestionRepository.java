package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findAllByAuthor_Registration(String user);
}
