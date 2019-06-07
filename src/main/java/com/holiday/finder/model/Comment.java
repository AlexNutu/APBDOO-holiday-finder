package com.holiday.finder.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min=5, message = "Cel putin 5 litere")
    @NotNull
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Place place;

}
