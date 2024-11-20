package com.example.demo.controller;

import com.example.demo.dto.request.CommandBookDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookPageController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public String getBooks(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int bookPage) {
        final int PAGE_SIZE = 5;

        Pageable bookPageable = PageRequest.of(bookPage, PAGE_SIZE);

        Page<Book> bookPageResult = bookService.findAll(bookPageable);

        model.addAttribute("books", bookPageResult.getContent());
        model.addAttribute("booksCurrentPage", bookPageResult.getNumber());
        model.addAttribute("booksTotalPages", bookPageResult.getTotalPages());

        if (!model.containsAttribute("commandBookDto")) {
            model.addAttribute("commandBookDto", CommandBookDto.builder().build());
        }

        return "books";
    }

    @GetMapping("/update/{id}")
    public String updateBookForm(Model model, @PathVariable("id") String id) {
        Book existingBook = bookService.findOne(id);

        model.addAttribute("bookId", existingBook.getIsbn());

        if (!model.containsAttribute("commandBookDto")) {
            model.addAttribute("commandBookDto",
                    CommandBookDto.builder()
                            .author(existingBook.getAuthor())
                            .title(existingBook.getTitle())
                            .build()
            );
        }

        return "books_update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(
            @Valid @ModelAttribute("commandBookDto") CommandBookDto commandBookDto,
            BindingResult bindingResult,
            @PathVariable("id") String id,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commandBookDto", bindingResult);
            redirectAttributes.addFlashAttribute("commandBookDto", commandBookDto);
            return "redirect:/book/update/" + id;
        }

        bookService.update(bookMapper.toModel(commandBookDto), id);

        return "redirect:/book";
    }

    @PostMapping("/add")
    public String addBook(
            @Valid @ModelAttribute("commandBookDto") CommandBookDto commandBookDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commandBookDto", bindingResult);
            redirectAttributes.addFlashAttribute("commandBookDto", commandBookDto);
            return "redirect:/book";
        }

        bookService.create(bookMapper.toModel(commandBookDto));

        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        bookService.delete(id);
        return "redirect:/book";
    }
}
