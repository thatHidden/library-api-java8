package com.example.demo.mapper;

import com.example.demo.dto.request.CommandBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    ResponseBookDto toDto(Book model);

    Book toModel(CommandBookDto dto);
}
