package com.example.demo.mapper;

import com.example.demo.dto.request.CommandBorrowDto;
import com.example.demo.dto.response.ResponseBorrowDto;
import com.example.demo.model.Borrow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookMapper.class, ClientMapper.class})
public interface BorrowMapper {

    @Mapping(target = "responseBookDto", source = "book")
    @Mapping(target = "responseClientDto", source = "client")
    ResponseBorrowDto toDto(Borrow model);

    @Mapping(target = "book.isbn", source = "bookIsbn")
    @Mapping(target = "client.id", source = "clientId")
    Borrow toModel(CommandBorrowDto dto);
}
