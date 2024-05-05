package com.example.curvasbackmvp.models.exam;

import java.util.List;

public record AnswerDTO (String userId, String formId, List<AnswerQuestionDTO> questionsAwnsered) {}
