package com.example.simplegraphql.repository;

import com.example.simplegraphql.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ikechi Ucheagwu
 * @created 10/03/2023 - 02:54
 * @project SimpleGraphQL
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByBookId(int bookId);
}
