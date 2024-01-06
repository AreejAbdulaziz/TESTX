package com.example.makhzan.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "enter name more than 4 letters")
    @Pattern(regexp = "^[\\p{Alpha} ]*$",message = "only characters")
    @Column(columnDefinition = "varchar(20) not null" )
    private String name;
    @NotNull(message = "size cannot be null")
    @Pattern(regexp = "SMALL|MEDIUM|LARGE|OUTSIDE|COMPANY",message = "size should be SMALL, MEDIUM, LARGE, OUTSIDE or COMPANY.")
    @Column(columnDefinition = "varchar(20) not null")
    private String size;
    @NotNull(message = "price cannot be null")
    @Positive(message = "enter correct price")
    @Column(columnDefinition = "double not null")
    private Double price;
    @NotNull(message = "address cannot be null")
    @Column(columnDefinition = "varchar(200) not null")
    private String address;
    @NotNull(message = "city cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String city;
//    @Column(columnDefinition = "varchar ")
    private String status;
//    @Column(columnDefinition = "Boolean ")
    private Boolean available;
    @Column(columnDefinition = "int ")
    private Integer rentedTimes;
    @Column(columnDefinition = "double ")
    private Double rating;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "storage")
    private Set<Media> medias;
    @ManyToOne
    @JoinColumn(name = "landlordid")
    @JsonIgnore
    private Landlord landlord;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "storage")
    private Set<Orders> orders;


}
