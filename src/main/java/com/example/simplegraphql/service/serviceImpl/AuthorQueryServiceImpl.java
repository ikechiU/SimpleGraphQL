package com.example.simplegraphql.service.serviceImpl;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.simplegraphql.entity.Author;
import com.example.simplegraphql.repository.AuthorRepository;
import com.example.simplegraphql.service.AuthorQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 13/03/2023 - 10:42
 * @project SImpleGraphQL
 */

@Component
@AllArgsConstructor
public class AuthorQueryServiceImpl implements AuthorQueryService, GraphQLQueryResolver {
    private final AuthorRepository authorRepository;

    /**
     * Every field in the schema root query should have a method in the Query Resolver class with same name
     *     findAllAuthors: [Author]!
     *     countAuthors: Int!
     *     findAuthorById(authorId: ID): Author
     */

    @Override
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAllAuthorsPaged(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Author> pagedAuthor = authorRepository.findAll(pageable);
        return pagedAuthor.getContent();
    }

    @Override
    public Integer countAuthors() {
        return Math.toIntExact(authorRepository.count());
    }

    @Override
    public Author findAuthorById(int authorId) {
        return authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("Author does not exist"));
    }


}
