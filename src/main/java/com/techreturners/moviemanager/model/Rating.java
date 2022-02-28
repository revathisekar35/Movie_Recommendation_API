package com.techreturners.moviemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false,name="ID")
    Long Id;

    @Column
    Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    Movie movie;

    @Column
    Long likes;

    @Column
    Long dislikes;

    @Column
    Double totalRating;
}

