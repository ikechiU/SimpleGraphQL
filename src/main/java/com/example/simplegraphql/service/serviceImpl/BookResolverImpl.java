package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.entity.Book;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.service.BookResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 11:21
 * @project SImpleGraphQL
 * /

/**
 * For complex fields like author in Book, we have to resolve the values of those field.
 * N/B: If the client want to get a Book without the author field, the GraphQL server will
 * never do the work to retrieve it. So, the getAuthor() method will not be executed.
 */

@Service
@AllArgsConstructor
public class BookResolverImpl implements BookResolver, GraphQLResolver<Book> {
    private final AuthorRepository authorRepository;

    @Override
    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthorDetails().getAuthorId())
                .orElseThrow(()-> new RuntimeException("Author needed"));
    }
}
