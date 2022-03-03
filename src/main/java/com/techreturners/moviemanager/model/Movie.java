package com.techreturners.moviemanager.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
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
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date releaseDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MOVIE_PERSON", joinColumns = { @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(referencedColumnName = "id") })
	List<Person> person;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
}
