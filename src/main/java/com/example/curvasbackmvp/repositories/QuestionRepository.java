package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {
    Page<Question> findAllByAuthor_Registration(String user,
                                                Pageable pageable);
    Page<Question> findAllByDescriptionContaining(String description, Pageable pageable);
    Page<Question> findAllByDescription(String description, Pageable pageable);
    List<Question> findAllByDifficulty(String difficulty);
}
