package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams")
public class Exam {
    @Id
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "exam_questions",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    private Integer points;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;
}
