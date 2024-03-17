package com.example.curvasbackmvp.models.group;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String image;
    @ManyToMany
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    @OneToMany(mappedBy = "group")
    private List<Exam> exams;
//    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts;
    public Group(String name, String description, String image, List<User> users, List<Exam> exams /*, List<Post> posts*/) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.users = users;
        this.exams = exams;
//        this.posts = posts;
    }
}
