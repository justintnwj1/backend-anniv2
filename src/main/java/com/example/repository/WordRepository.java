package com.example.demo.repository;

import com.example.demo.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    // Spring Data JPA sudah menyediakan implementasi dasar untuk operasi CRUD
}
