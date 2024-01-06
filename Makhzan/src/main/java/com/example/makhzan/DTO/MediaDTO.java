package com.example.makhzan.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaDTO {
    @Positive(message = "enter correct id")
    @NotNull(message = "storage id cannot be null")
    private Integer storage_id;
    @NotNull(message = "URL cannot be null")
    //@URL
    private String url;
    @NotEmpty(message = "Type should not be empty")
    @Pattern(regexp = "IMAGE|VIDEO|DOCUMENT", message = "Type of media should not be empty")
    private String type;
}
