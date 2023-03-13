package com.example.simplegraphql.controller;

import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.service.serviceImpl.BookResolver;
import com.example.simplegraphql.service.serviceImpl.Mutation;
import com.example.simplegraphql.service.serviceImpl.Query;
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
public class AuthorController {

    private final Query query;
    private final Mutation mutation;
    private final BookResolver bookResolver;

    @QueryMapping
    public List<Author> findAllAuthors() {
        List<Author> result = new ArrayList<>();
        query.findAllAuthors().forEach(result::add);
        return result;
    }

    @QueryMapping
    public List<Author> findAllAuthorsPaged(@Argument int page, @Argument int limit) {
        return query.findAllAuthorsPaged(page, limit);
    }

    @QueryMapping
    public int countAuthors() {
        return query.countAuthors();
    }

    @QueryMapping
    public Author findAuthorById(@Argument int authorId) {
        return query.findAuthorById(authorId);
    }

    @QueryMapping
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        query.findAllBooks().forEach(books::add);
        return books;
    }

    @QueryMapping
    public List<Book> findAllBooksPaged(@Argument int page, @Argument int limit) {
        return query.findAllBooksPaged(page, limit);
    }

    @QueryMapping
    public int countBooks() {
        return query.countBooks();
    }

    @QueryMapping
    public Book findBookById(@Argument int bookId) {
        return query.findBookById(bookId);
    }

    /**
     * Maps the handler method to a field with the same name in the schema
     */
    @SchemaMapping
    public Author author(Book book) {
        return bookResolver.getAuthor(book);
    }

    @MutationMapping
    public Author createAuthor(@Argument String firstName, @Argument String lastName, @Argument int age) {
        return mutation.createAuthor(firstName, lastName, age);
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument int pageCount, @Argument int authorId) {
        return mutation.createBook(name, pageCount, authorId);
    }

    @MutationMapping
    public Book updateBook(@Argument int bookId, @Argument String name, @Argument int pageCount) {
        return mutation.updateBook(bookId, name, pageCount);
    }

    @MutationMapping
    public boolean deleteBook(@Argument int bookId) {
        return mutation.deleteBook(bookId);
    }

}
