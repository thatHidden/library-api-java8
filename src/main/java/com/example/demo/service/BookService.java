package com.example.demo.service;

import com.example.demo.exception.EntityConflictException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    @Transactional(readOnly = true)
    public Book findOne(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
    }

    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional
    public Book create(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new EntityConflictException();
        }
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Book book, String id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        bookValidator.validate(existingBook);
        return bookRepository.save(existingBook);
    }

    @Transactional
    public void delete(String id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        bookRepository.delete(existingBook);
    }
}
