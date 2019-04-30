package com.holiday.finder.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Cel putin 2 litere")
    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private Set<Place> places = new HashSet<>();

}
