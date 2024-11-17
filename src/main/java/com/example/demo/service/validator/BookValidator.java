package com.example.demo.service.validator;


import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator implements Validator<Book> {

    public void validate(Book book) {
        checkTitle(book.getTitle());
        checkAuthor(book.getAuthor());
        checkIsbn(book.getIsbn());
    }

    private void checkTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() > 50) {
            throw new BadRequestException("bad title");
        }
    }

    private void checkAuthor(String author) {
        if (author == null || author.trim().isEmpty() || author.length() > 40) {
            throw new BadRequestException("bad author");
        }
    }

    private void checkIsbn(String isbn) {
        if (isbn == null || isbn.trim().length() != 13) {
            throw new BadRequestException("bad isbn");
        }
    }
}
