package com.example.curvasbackmvp.models.question;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;
    private String description;
    private String image;
    // Lista de alternativas
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Alternative> alternatives;
    private String tip; // TODO: ser desbloqueada após algum gatilho (acertar x numero de questões)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private String source;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    @JsonIgnoreProperties(value = "groups")
    private Teacher author;
    @ManyToMany(mappedBy = "questions")
    @JsonBackReference
    @JsonIgnoreProperties(value = "questions")
    private List<Exam> exam;
}
