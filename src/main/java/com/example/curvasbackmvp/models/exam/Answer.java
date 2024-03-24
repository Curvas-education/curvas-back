package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.question.Alternative;
import com.example.curvasbackmvp.models.question.Question;
import com.example.curvasbackmvp.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "alternative_id")
    private Alternative alternative;

}