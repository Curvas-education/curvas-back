package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
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
    @OneToMany(mappedBy = "exam")
    @JsonBackReference
    private List<Answer> answers;
    @ManyToMany
    @JoinTable(
            name = "group_exams",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonIgnoreProperties(value = "exams") // Evitar recursão
    private List<Group> groups;
    private Integer points;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;
    // Campos apenas para referencias
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Exam(ExamRequestDTO examData) {
        this.name = examData.getName();
        this.description = examData.getDescription();
        this.points = examData.getPoints();
        this.startTime = examData.getStartDate();
        this.endTime = examData.getEndDate();
    }
}
