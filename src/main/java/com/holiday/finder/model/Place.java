package com.holiday.finder.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Title needs at least 3 letters")
    @NotBlank(message = "Title cannot pe blank")
    @NotNull
    private String title;

    @Size(min = 10, max = 500,  message = "Description needs between 10 and 500 letters")
    @NotBlank(message = "The description cannot pe blank")
    @NotNull
    private String description;

    @NotNull
    @Min(value = 10, message = "Min cost of a holiday is 10")
    private Double minPrice;

    @NotNull
    @Max(value = 1000, message = "Max cost of a holiday is 1000")
    private Double maxPrice;

    @NotNull
    @NotBlank(message = "Weather cannot pe blank")
    private String weather;

    @NotNull
    @NotBlank(message = "Image source cannot pe blank")
    private String imageSrc;

    @Transient
    private Set<String> imagesPaths = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "place_category", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private Destination destination;

    @ManyToOne
    private Season season;

}
