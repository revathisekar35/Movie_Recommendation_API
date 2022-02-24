package com.techreturners.moviemanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	@GeneratedValue
	@Column(updatable = false, nullable = false,name="ID")
	Long id;
	
	@Column
	String name;
	
	@Column
	String description;
	
	@Column
	Date releasYear;
}
