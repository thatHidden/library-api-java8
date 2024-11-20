package com.example.demo.controller;

import com.example.demo.dto.request.CommandBorrowDto;
import com.example.demo.mapper.BorrowMapper;
import com.example.demo.model.Borrow;
import com.example.demo.service.BorrowService;
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
import java.util.UUID;

@Controller
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowPageController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @GetMapping
    public String getBorrows(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int borrowPage) {
        final int PAGE_SIZE = 5;

        Pageable borrowPageable = PageRequest.of(borrowPage, PAGE_SIZE);

        Page<Borrow> borrowPageResult = borrowService.findAll(borrowPageable);

        model.addAttribute("borrows", borrowPageResult.getContent());
        model.addAttribute("borrowsCurrentPage", borrowPageResult.getNumber());
        model.addAttribute("borrowsTotalPages", borrowPageResult.getTotalPages());

        if (!model.containsAttribute("commandBorrowDto")) {
            model.addAttribute("commandBorrowDto", CommandBorrowDto.builder().build());
        }

        return "borrows";
    }

    @PostMapping("/add")
    public String addBorrow(
            @Valid @ModelAttribute("commandBorrowDto") CommandBorrowDto commandBorrowDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commandBorrowDto", bindingResult);
            redirectAttributes.addFlashAttribute("commandBorrowDto", commandBorrowDto);
            return "redirect:/borrow";
        }

        borrowService.create(borrowMapper.toModel(commandBorrowDto));

        return "redirect:/borrow";
    }


    @GetMapping("/delete/{id}")
    public String deleteBorrow(@PathVariable("id") UUID id) {
        borrowService.delete(id);
        return "redirect:/borrow";
    }
}
