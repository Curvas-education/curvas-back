package com.example.curvasbackmvp.models.student;

import com.example.curvasbackmvp.models.user.RegisterDTO;
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
@DiscriminatorValue("STUDENT")
public class Student extends User {
    public Student(String registration, String name, String email, String phone, String about, String cpf, Boolean active, Date birthDate, UserRole userRole) {
        super(registration, name, email, phone, about, cpf, active, birthDate, userRole);
    }
    public Student() {
    }

    public Student(RegisterDTO registerDTO) {
    }
}
