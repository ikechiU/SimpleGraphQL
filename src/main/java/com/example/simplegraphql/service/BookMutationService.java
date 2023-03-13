package com.example.simplegraphql.service;

import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 17:32
 * @project SImpleGraphQL
 */

public interface BookMutationService {
    Book createBook(String name, int pageCount, int authorId);

    Book updateBook(int bookId, String name, int pageCount);

    boolean deleteBook(int id);
}
