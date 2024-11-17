package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    @JsonProperty(value = "time_stamp", index = 1)
    private String timeStamp;

    private String path;

    private String method;

    private String message;
}
