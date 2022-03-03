
# Movie Recommendation API

## Introduction

This is the Movie recommendation API application which will give you information about movie details based on our input using a Test-Driven Development approach.

### Pre-Requisites
- Java SE Development Kit 11
- Maven


### Technologies & Dependencies
- Spring Boot
- Spring Web
- MySQL Database (local), Amazon RDS MySQL (cloud)
- Lombok
- Spring Data JPA
- Actuator
- Swagger documentation
- Twilio Message service

### How to Get Started
- Fork this repo to your Github and then clone the forked version of this repo


### Main Entry Point
- The Main Entry Point for the application is: [MovieApplication.java](src/main/java/com/techreturners/moviemanager/MovieApplication.java)


### Description
 This movie Recommendation API project is a Spring Boot application. We can Add/Update/Delete movies. Also we can get details about a movie based on our API input and data availability.
 
### The Recommended input to get a result is:
- Id
- Genre
- Language
- Release year
- Ratings
- Certification
- Actor/Director name
- Country
 Also we can get user's comments for the movie.
 
 ### Running the Unit Tests
- You can run the unit tests in IntelliJ/Eclipse, or you can go to your terminal and inside the root of this directory, run:

`mvn test`

Future Thoughts:
- Can restrict to get movie based on age.
- Can give permission to perform API based on the user role.


