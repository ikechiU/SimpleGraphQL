package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.service.AuthorMutationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 10:52
 * @project SImpleGraphQL
 */

@Component
@AllArgsConstructor
public class AuthorMutationServiceImpl implements AuthorMutationService, GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    /**
     * Every field in the schema root query should have a method in the Query Resolver class with same name
     *    createAuthor(firstName: String!, lastName: String!, age: Int!): Author

     *    createBook(name: String!, pageCount: Int!, author: Int!): Book
     *    updateBook(bookId: Int!, name: String!, pageCount: Int!): Book
     *    deleteBook(bookId: ID!): Boolean
     */

    @Override
    public Author createAuthor(String firstName, String lastName, int age) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }


}
