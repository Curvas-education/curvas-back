package com.example.curvasbackmvp.services;

import com.example.curvasbackmvp.models.question.Alternative;
import com.example.curvasbackmvp.repositories.AlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlternativeService {
    @Autowired
    AlternativeRepository alternativeRepository;

    public Alternative findById(String id) {
        return alternativeRepository.findById(id).orElseThrow();
    }
}
