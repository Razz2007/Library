package com.racinger.librarySystem.Library.DTO;

public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private String nationality;
    private Integer birthYear;

    public AuthorDto() {}

    public AuthorDto(Long id, String firstName, String lastName, String biography, String nationality, Integer birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.nationality = nationality;
        this.birthYear = birthYear;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
}