package com.techreturners.moviemanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, name = "ID")
    Long id;

    @Column
    String comment;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
