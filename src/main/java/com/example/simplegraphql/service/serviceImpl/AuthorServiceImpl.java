//package com.example.simplegraphql.service.serviceImpl;
//
//import com.example.simplegraphql.entity.Author;
//import com.example.simplegraphql.pojo.AuthorRequest;
//import com.example.simplegraphql.pojo.AuthorResponse;
//import com.example.simplegraphql.repository.AuthorRepository;
//import com.example.simplegraphql.repository.BookRepository;
//import com.example.simplegraphql.service.AuthorService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author Ikechi Ucheagwu
// * @created 10/03/2023 - 02:50
// * @project SImpleGraphQL
// */
//
//@AllArgsConstructor
//@Service
//public class AuthorServiceImpl implements AuthorService {
//
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//
//    @Override
//    public AuthorResponse createAuthor(AuthorRequest request) {
//        Author author = authorRepository.save(AuthorRequest.mapFromAuthorRequest(request));
//        return AuthorResponse.mapFromAuthor(author);
//    }
//
//    @Override
//    public AuthorResponse getAuthor(long authorId) {
//        Author author = authorRepository.findById(authorId)
//                .orElseThrow(() -> new RuntimeException("Author does not exist"));
//
//        return AuthorResponse.mapFromAuthor(author);
//    }
//
//    @Override
//    public List<AuthorResponse> getAuthors() {
//        return null;
//    }
//}
