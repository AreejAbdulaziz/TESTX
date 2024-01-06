package com.example.makhzan.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {

    private Integer userid;
    private Integer storageid;
    @NotNull(message = "rating cannot be null")
    @Positive(message = "enter correct rating")
    private Integer rating;
    @NotNull(message = "comment cannot be null")
    private String comment;
}
