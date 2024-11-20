package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CommandBorrowDto {
    @JsonProperty(value = "book_isbn")
    @Size(min = 13, max = 13)
    private String bookIsbn;

    @JsonProperty(value = "client_id")
    @NotNull
    private UUID clientId;
}
