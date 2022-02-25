package com.techreturners.moviemanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false,name="ID")
    Long ID;

    @Column
    Long userID;

    @Column
    Long movieID;

    @Column
    Long likes;

    @Column
    Long dislikes;

    @Column
    Double totalRating;
}

