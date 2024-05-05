package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.group.GroupDTO;
import com.example.curvasbackmvp.models.question.Question;

import java.time.LocalDateTime;
import java.util.List;

public record ExamDetails(String id, String name, String description, String author, List<Question> questionList, List<GroupDTO> groups, Integer points, LocalDateTime startTime, LocalDateTime endTime) {
    public ExamDetails(Exam exam) {
        this(exam.getId(), exam.getName(), exam.getDescription(), exam.getTeacher().getName(), exam.getQuestions(), exam.getGroups().stream().map(GroupDTO::new).toList(), exam.getPoints(), exam.getStartTime(), exam.getEndTime());
    }
}
