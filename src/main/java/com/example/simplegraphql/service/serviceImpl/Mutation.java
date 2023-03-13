package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 10:52
 * @project SImpleGraphQL
 */

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    /**
     * Every field in the schema root query should have a method in the Query Resolver class with same name
     *    createAuthor(firstName: String!, lastName: String!, age: Int!): Author

     *    createBook(name: String!, pageCount: Int!, author: Int!): Book
     *    updateBook(bookId: Int!, name: String!, pageCount: Int!): Book
     *    deleteBook(bookId: ID!): Boolean
     */

    public Author createAuthor(String firstName, String lastName, int age) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }

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

    public Book updateBook(int bookId, String name, int pageCount) {
        Book existingBook = bookRepository.findByBookId(bookId).orElseThrow(()-> new RuntimeException("Not found"));
        existingBook.setName(name);
        existingBook.setPageCount(pageCount);
        existingBook.setBookId(bookId);

        Book updatedBook = bookRepository.save(existingBook);
        return updatedBook;
    }

    public boolean deleteBook(int id) {
        bookRepository.findByBookId(id).orElseThrow(()-> new RuntimeException("Not found"));
        bookRepository.deleteById(id);
        return true;
    }


}
