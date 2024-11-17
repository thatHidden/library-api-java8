package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ResponseClientDto {
    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "birth_date")
    private LocalDate birthDate;
}
