package com.example.curvasbackmvp.models.user;

import com.example.curvasbackmvp.models.group.Group;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Entity
@Table(name = "users") // TODO: resolver problema na geração do SQL
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "registration")
public abstract class User implements UserDetails {
    @Id
    @Column(unique = true)
    private String registration;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String about;
    @Column(unique = true)
    private String cpf;
    private Boolean active = true;
    private Date birthDate;
    private UserRole userRole;
    @ManyToMany(mappedBy = "users")
    @JsonBackReference
    @JsonIgnoreProperties(value = "users") // Evitar recursão
    private List<Group> groups;

    public User(String email, String encryptedPassword, UserRole userRole) {
        this.email = email;
        this.password = encryptedPassword;
        this.userRole = userRole;
    }

    public User(String registration, String name, String email, String phone, String about, String cpf, Boolean active, Date birthDate, UserRole userRole) {
    }
    // TODO: adicionar os grupos

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userRole == UserRole.ADMIN || this.userRole == UserRole.MANAGER) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_TEACHER"),
                new SimpleGrantedAuthority("ROLE_STUDENT")
        );


        else return List.of(new SimpleGrantedAuthority("ROLE_" + this.userRole));


    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}