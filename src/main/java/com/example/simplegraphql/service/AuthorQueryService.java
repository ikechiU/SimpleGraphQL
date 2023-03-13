package com.example.simplegraphql.service;

import com.example.simplegraphql.entity.Author;

import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 17:32
 * @project SImpleGraphQL
 */

public interface AuthorQueryService {
    Iterable<Author> findAllAuthors();

    List<Author> findAllAuthorsPaged(int page, int limit);
    Integer countAuthors();

    Author findAuthorById(int authorId);

}
