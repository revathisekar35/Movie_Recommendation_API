package com.techreturners.moviemanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, name = "ID")
    Long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    Date releaseYear;

    @OneToMany(mappedBy = "movie", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
