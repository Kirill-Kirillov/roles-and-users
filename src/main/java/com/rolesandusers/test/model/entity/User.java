package com.rolesandusers.test.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String userName;

    @Email
    @Column(unique = true)
    private String email;

    private LocalDate dateOfBirth;

    private boolean deleted;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_name") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @JsonIgnoreProperties("users")
    private Set<Role> roles = new HashSet<>();

}
