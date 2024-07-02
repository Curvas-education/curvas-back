package com.example.curvasbackmvp.models.teacher;

import com.example.curvasbackmvp.models.user.User;
import com.example.curvasbackmvp.models.user.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    private String cvLattes;
    private String website;

    public Teacher(String registration, String name, String email, String phone, String about, String cpf, Boolean active, Date birthDate, String cvLattes, String website, UserRole userRole) {
        super(registration, name, email, phone, about, cpf, active, birthDate, userRole);
        this.cvLattes = cvLattes;
        this.website = website;
    }
    public Teacher() {

    }
}
