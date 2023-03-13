package com.example.simplegraphql.controller;

import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.service.serviceImpl.AuthorMutationServiceImpl;
import com.example.simplegraphql.service.serviceImpl.AuthorQueryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

    private final AuthorQueryServiceImpl authorQueryService;
    private final AuthorMutationServiceImpl authorMutationService;

    @QueryMapping
    public List<Author> findAllAuthors() {
        List<Author> result = new ArrayList<>();
        authorQueryService.findAllAuthors().forEach(result::add);
        return result;
    }

    @QueryMapping
    public List<Author> findAllAuthorsPaged(@Argument int page, @Argument int limit) {
        return authorQueryService.findAllAuthorsPaged(page, limit);
    }

    @QueryMapping
    public int countAuthors() {
        return authorQueryService.countAuthors();
    }

    @QueryMapping
    public Author findAuthorById(@Argument int authorId) {
        return authorQueryService.findAuthorById(authorId);
    }

    @MutationMapping
    public Author createAuthor(@Argument String firstName, @Argument String lastName, @Argument int age) {
        return authorMutationService.createAuthor(firstName, lastName, age);
    }
}
