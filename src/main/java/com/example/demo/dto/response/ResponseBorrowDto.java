package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ResponseBorrowDto {
    @JsonProperty(value = "book")
    private ResponseBookDto responseBookDto;

    @JsonProperty(value = "client")
    private ResponseClientDto responseClientDto;

    private LocalDate date;
}
