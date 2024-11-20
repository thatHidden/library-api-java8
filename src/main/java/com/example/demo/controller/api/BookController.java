package com.example.demo.controller.api;


import com.example.demo.dto.request.CommandBookDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper clientMapper;

    @GetMapping("/{id}")
    private ResponseBookDto getBook(@PathVariable("id") String id) {
        return clientMapper.toDto(bookService.findOne(id));
    }

    @GetMapping()
    private Page<ResponseBookDto> getAllBooks(@PageableDefault Pageable pageable) {
        return bookService.findAll(pageable).map(clientMapper::toDto);
    }

    @PostMapping()
    public ResponseBookDto createBook(@RequestBody CommandBookDto dto) {
        return clientMapper.toDto(bookService.create(clientMapper.toModel(dto)));
    }

    @PutMapping("/{id}")
    public ResponseBookDto updateBook(@RequestBody CommandBookDto dto,
                                      @PathVariable String id) {
        return clientMapper.toDto(bookService.update(clientMapper.toModel(dto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.delete(id);
    }
}
