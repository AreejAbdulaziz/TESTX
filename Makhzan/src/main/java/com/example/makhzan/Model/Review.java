package com.example.makhzan.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "rating cannot be null")
    @Positive(message = "enter correct rating")
    @Column(columnDefinition = "int not null")
    private Integer rating;
    @NotNull(message = "comment cannot be null")
    @Column(columnDefinition = "varchar(200) not null")
    private String comment;

    @ManyToOne
    @JoinColumn(name="storageid")
    @JsonIgnore
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "reviewid")
    @JsonIgnore
    private Customer customer;


}
