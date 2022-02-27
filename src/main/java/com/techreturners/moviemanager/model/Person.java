package com.techreturners.moviemanager.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	@Id
	@GeneratedValue
	@Column(nullable = false,name="ID")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private PersonRole role;
	

}
