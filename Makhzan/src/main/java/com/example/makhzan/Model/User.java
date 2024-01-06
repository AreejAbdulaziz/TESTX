package com.example.makhzan.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "password cannot be null")
    @Size(min = 6,message = "password must be more than 6 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "password must contain at least one number and one uppercase, lowercase letter and special character, and at least 8 or more characters")
    private String password;
    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 20,message = "name must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar (20) not null")
    private String name;
    @Email(message = "enter correct email")
    @Column(columnDefinition = "varchar (20) unique")
    private String email;
    @Pattern(regexp = "^(05\\d{8})$")
    @NotNull(message = "phone number cannot be null")
    @Positive(message = "enter correct number")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
    @Pattern(regexp = "^(CUSTOMER|LANDLORD|ADMIN)$")
    @Column(columnDefinition = "varchar(20) ")
    //check (role = 'CUSTOMER' or role = 'LANDLORD' or role = 'ADMIN')
    private String role;



    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Landlord landlord;

}
