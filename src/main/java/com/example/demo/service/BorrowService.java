package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Borrow;
import com.example.demo.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final ClientService clientService;

    @Transactional(readOnly = true)
    public Borrow findOne(UUID id) {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("borrow not found"));
    }

    @Transactional(readOnly = true)
    public Page<Borrow> findAll(Pageable pageable) {
        return borrowRepository.findAll(pageable);
    }

    @Transactional
    public Borrow create(Borrow borrow) {
        borrow.setDate(LocalDate.now());
        borrow.setBook(bookService.findOne(borrow.getBook().getIsbn()));
        borrow.setClient(clientService.findOne(borrow.getClient().getId()));
        return borrowRepository.save(borrow);
    }

    @Transactional
    public void delete(UUID id) {
        Borrow existingBorrow = borrowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("borrow not found"));
        borrowRepository.delete(existingBorrow);
    }
}
