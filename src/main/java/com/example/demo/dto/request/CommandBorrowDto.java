package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CommandBorrowDto {
    @JsonProperty(value = "book_isbn")
    private String bookIsbn;

    @JsonProperty(value = "client_id")
    private UUID clientId;
}
