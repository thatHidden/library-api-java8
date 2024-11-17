package com.example.demo.repository;

import com.example.demo.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {
}
