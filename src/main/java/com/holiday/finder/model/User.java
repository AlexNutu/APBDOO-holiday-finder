package com.holiday.finder.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PrimaryKeyJoinColumn
    @Size(min = 2, message = "Username has at least 2 letters")
    @NotBlank(message = "Username cannot pe blank")
    private String username;

    @NotNull
    @Size(min = 5, message = "Password has at least 5 letters")
    @NotBlank(message = "Password cannot pe blank")
    private String password;

    private Integer enabled;

    @NotNull
    @NotBlank(message = "Email cannot pe blank")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

}
