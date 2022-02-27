package com.techreturners.moviemanager.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	@GeneratedValue
	@Column(updatable = false, nullable = false, name = "ID")
	Long id;

	@Column
	String name;

	@Column
	String description;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date releasYear;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "MOVIE_PERSON", joinColumns = { @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(referencedColumnName = "id") })
	List<Person> person;
}
