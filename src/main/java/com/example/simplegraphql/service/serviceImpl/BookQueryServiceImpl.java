package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.repository.BookRepository;
import com.example.simplegraphql.service.BookQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 10:42
 * @project SImpleGraphQL
 */

@Component
@AllArgsConstructor
public class BookQueryServiceImpl implements BookQueryService, GraphQLQueryResolver {
    private final BookRepository bookRepository;

    /**
     * Every field in the schema root query should have a method in the Query Resolver class with same name
     *     findAllBooks: [Book]!
     *     countBooks: Int!
     *     findBookById(bookId: ID): Book
     */

    @Override
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllBooksPaged(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Book> pagedBook = bookRepository.findAll(pageable);
        return pagedBook.getContent();
    }

    @Override
    public Integer countBooks() {
        return Math.toIntExact(bookRepository.count());
    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book does not exist"));
    }

}
