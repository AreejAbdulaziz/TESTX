package com.example.makhzan.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrdersDTO {
    private Integer userid;
    private Integer storageid;
    private double totalPrice;
    @FutureOrPresent(message = "write start date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    @NotNull(message = "date cannot be null!")
    private LocalDate startDate;
    @FutureOrPresent(message = "write end date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    @NotNull(message = "date cannot be null!")
    private LocalDate endDate;
    @Pattern(regexp = "ACCEPTED|PENDING|REJECT",message = "Status should be ACCEPTED, PENDING or REJECT.")
    private String status;
    private LocalDate orderDate;
}
