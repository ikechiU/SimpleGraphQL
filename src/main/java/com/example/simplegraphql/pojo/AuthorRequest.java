//package com.example.simplegraphql.pojo;
//
//import com.example.simplegraphql.entity.Author;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * @author Ikechi Ucheagwu
// * @created 10/03/2023 - 03:35
// * @project SImpleGraphQL
// */
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AuthorRequest {
//    private String firstName;
//    private String lastName;
//    private long authorId;
//
//    public static Author mapFromAuthorRequest(AuthorRequest authorRequest) {
//        return Author.builder()
//                .firstName(authorRequest.firstName)
//                .lastName(authorRequest.lastName)
//                .authorId(authorRequest.authorId)
//                .build();
//    }
//}
