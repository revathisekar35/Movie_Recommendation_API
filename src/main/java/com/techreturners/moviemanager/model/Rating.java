package com.techreturners.moviemanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false,name="ID")
    Long Id;

    /*@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;*/
    @Column
    Long user;

    @JsonManagedReference
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

