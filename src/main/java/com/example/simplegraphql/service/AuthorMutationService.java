package com.example.simplegraphql.service;

import com.example.simplegraphql.entity.Author;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 17:32
 * @project SImpleGraphQL
 */

public interface AuthorMutationService {
    Author createAuthor(String firstName, String lastName, int age);
}
