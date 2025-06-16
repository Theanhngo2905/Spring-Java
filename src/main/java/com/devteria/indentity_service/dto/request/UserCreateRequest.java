package com.devteria.indentity_service.dto.request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;



public class UserCreateRequest {
    private String firstName;
    private String lastName;

    private String username;

    @Size(min = 8, max = 20, message = "Password phải lơn hơn 8 ký tự")
    private String password;
    private LocalDate dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
