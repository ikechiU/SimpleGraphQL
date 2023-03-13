package com.example.simplegraphql.service;

import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 17:32
 * @project SImpleGraphQL
 */

public interface BookResolver {
    Author getAuthor(Book book);
}
