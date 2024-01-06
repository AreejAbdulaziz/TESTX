package com.example.makhzan.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LandlordDTO {
    @NotNull(message = "password cannot be null")
    @Size(min = 6,message = "password must be more than 6 characters")
    private String password;
    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 20,message = "name must be between 2 and 20 characters")
    private String name;
    @Email(message = "enter correct email")
    private String email;
    @Pattern(regexp = "^(05\\d{8})$")
    @NotNull(message = "phone number cannot be null")
    @Positive(message = "enter correct number")
    private String phoneNumber;
    @Pattern(regexp = "^(CUSTOMER|LANDLORD|ADMIN)$")
    @Column(columnDefinition = "varchar(20) ")
    private String role;


    @NotNull(message = "is company cannot be null")
    private Boolean isCompany;
    @NotNull(message = "license cannot be null")
    @Positive(message = "enter correct license")
    private String license;
}