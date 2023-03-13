package com.example.simplegraphql.service;

import com.example.simplegraphql.entity.Book;

import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 17:32
 * @project SImpleGraphQL
 */

public interface BookQueryService {
    Iterable<Book> findAllBooks();
    List<Book> findAllBooksPaged(int page, int limit);

    Integer countBooks();
    Book findBookById(int bookId);

}
