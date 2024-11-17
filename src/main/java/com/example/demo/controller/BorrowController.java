package com.example.demo.controller;

import com.example.demo.dto.request.CommandBorrowDto;
import com.example.demo.dto.response.ResponseBorrowDto;
import com.example.demo.mapper.BorrowMapper;
import com.example.demo.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @GetMapping("/{id}")
    private ResponseBorrowDto getBorrow(@PathVariable("id") UUID id) {
        return borrowMapper.toDto(borrowService.findOne(id));
    }

    @GetMapping()
    private Page<ResponseBorrowDto> getAllBorrows(@PageableDefault Pageable pageable) {
        return borrowService.findAll(pageable).map(borrowMapper::toDto);
    }

    @PostMapping()
    public ResponseBorrowDto createBorrow(@RequestBody CommandBorrowDto dto) {
        return borrowMapper.toDto(borrowService.create(borrowMapper.toModel(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable UUID id) {
        borrowService.delete(id);
    }
}
