package com.example.curvasbackmvp.models.group;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.teacher.Teacher;
import com.example.curvasbackmvp.models.user.User;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "groups")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String image;
    @ManyToMany
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    @JsonIgnoreProperties(value = "groups") // Evitar recursão
    private Set<User> users;
    @ManyToOne
    @JsonIgnoreProperties(value = "groups") // Evitar recursão
    private Teacher creator;
    @ManyToMany(mappedBy = "groups")
    @JsonIgnoreProperties(value = "groups") // Evitar recursão
    private List<Exam> exams;
//    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts;
    public Group(String name, String description, String image, Set<User> users, List<Exam> exams /*, List<Post> posts*/) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.users = users;
        this.exams = exams;
//        this.posts = posts;
    }

    public Group(GroupRequestDTO groupData) {
        this.name = groupData.getName();
        this.description = groupData.getDescription();
        this.image = groupData.getDescription();
    }
}
