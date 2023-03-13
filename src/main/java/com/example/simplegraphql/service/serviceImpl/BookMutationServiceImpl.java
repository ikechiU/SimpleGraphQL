package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.repository.BookRepository;
import com.example.simplegraphql.service.BookMutationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 10:52
 * @project SImpleGraphQL
 */

@Component
@AllArgsConstructor
public class BookMutationServiceImpl implements BookMutationService, GraphQLMutationResolver {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    /**
     * Every field in the schema root query should have a method in the Query Resolver class with same name
     *    createBook(name: String!, pageCount: Int!, author: Int!): Book
     *    updateBook(bookId: Int!, name: String!, pageCount: Int!): Book
     *    deleteBook(bookId: ID!): Boolean
     */

    @Override
    public Book createBook(String name, int pageCount, int authorId) {
        Author author = authorRepository.findByAuthorId(authorId).orElse(null);
        System.out.println("Author: " + author);

        Book book = Book.builder()
                .name(name)
                .pageCount(pageCount)
                .authorDetails(author)
                .build();

        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    @Override
    public Book updateBook(int bookId, String name, int pageCount) {
        Book existingBook = bookRepository.findByBookId(bookId).orElseThrow(()-> new RuntimeException("Not found"));
        existingBook.setName(name);
        existingBook.setPageCount(pageCount);
        existingBook.setBookId(bookId);

        Book updatedBook = bookRepository.save(existingBook);
        return updatedBook;
    }

    @Override
    public boolean deleteBook(int id) {
        bookRepository.findByBookId(id).orElseThrow(()-> new RuntimeException("Not found"));
        bookRepository.deleteById(id);
        return true;
    }


}
