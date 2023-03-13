package com.example.simplegraphql.controller;

import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.service.serviceImpl.BookMutationServiceImpl;
import com.example.simplegraphql.service.serviceImpl.BookQueryServiceImpl;
import com.example.simplegraphql.service.serviceImpl.BookResolverImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 10/03/2023 - 02:52
 * @project SImpleGraphQL
 */

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookQueryServiceImpl bookQueryService;
    private final BookMutationServiceImpl bookMutationService;
    private final BookResolverImpl bookResolver;

    @QueryMapping
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        bookQueryService.findAllBooks().forEach(books::add);
        return books;
    }

    @QueryMapping
    public List<Book> findAllBooksPaged(@Argument int page, @Argument int limit) {
        return bookQueryService.findAllBooksPaged(page, limit);
    }

    @QueryMapping
    public int countBooks() {
        return bookQueryService.countBooks();
    }

    @QueryMapping
    public Book findBookById(@Argument int bookId) {
        return bookQueryService.findBookById(bookId);
    }

    /**
     * Maps the handler method to a field with the same name in the schema
     */
    @SchemaMapping
    public Author author(Book book) {
        return bookResolver.getAuthor(book);
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument int pageCount, @Argument int authorId) {
        return bookMutationService.createBook(name, pageCount, authorId);
    }

    @MutationMapping
    public Book updateBook(@Argument int bookId, @Argument String name, @Argument int pageCount) {
        return bookMutationService.updateBook(bookId, name, pageCount);
    }

    @MutationMapping
    public boolean deleteBook(@Argument int bookId) {
        return bookMutationService.deleteBook(bookId);
    }
}
