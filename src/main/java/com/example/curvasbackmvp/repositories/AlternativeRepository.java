package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.question.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativeRepository extends JpaRepository<Alternative, String > {
}
