//package com.example.simplegraphql.pojo;
//
//import com.example.simplegraphql.entity.Author;
//import com.example.simplegraphql.entity.Book;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Ikechi Ucheagwu
// * @created 10/03/2023 - 03:22
// * @project SImpleGraphQL
// */
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class AuthorResponse {
//    private long authorId;
//    private String firstName;
//    private String lastName;
//    private List<Book> bookList;
//
//    public static AuthorResponse mapFromAuthor(Author author) {
//        return AuthorResponse.builder()
//                .authorId(author.getAuthorId())
//                .firstName(author.getFirstName())
//                .lastName(author.getLastName())
//                .bookList(author.getBookList())
//                .build();
//    }
//
//    public static List<AuthorResponse> mapFromAuthor(List<Author> authors) {
//        return authors.stream()
//                .map(AuthorResponse::mapFromAuthor)
//                .collect(Collectors.toList());
//    }
//}
