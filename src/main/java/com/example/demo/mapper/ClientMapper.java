package com.example.demo.mapper;

import com.example.demo.dto.request.CommandClientDto;
import com.example.demo.dto.response.ResponseClientDto;
import com.example.demo.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ResponseClientDto toDto(Client model);

    Client toModel(CommandClientDto dto);
}
