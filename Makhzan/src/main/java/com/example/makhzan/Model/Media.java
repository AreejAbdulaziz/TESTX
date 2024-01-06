package com.example.makhzan.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Media {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column( columnDefinition = "varchar(20) ")
        private String url;
        @Column( columnDefinition = "varchar(20) ")
        private String type;

        @ManyToOne
        @JoinColumn(name = "storageid")
        @JsonIgnore
        private Storage storage;
}
