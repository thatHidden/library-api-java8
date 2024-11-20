package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class CommandBookDto {
    @Size(max = 50, message = "Размер состоит из 13 символов")
    @NotEmpty
    private String title;

    @Size(max = 40, message = "Размер состоит из 13 символов")
    @NotEmpty
    private String author;

    @Size(min = 13, max = 13, message = "Размер состоит из 13 символов")
    private String isbn;
}
