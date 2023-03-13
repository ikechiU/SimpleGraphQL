//package com.example.simplegraphql.pojo;
//
//import com.example.simplegraphql.entity.Book;
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
//public class BookRequest {
//    private String name;
//    private int pageCount;
//    private long authorId;
//
//    public static Book mapFromBookRequest(BookRequest request) {
//        return Book.builder()
//                .name(request.getName())
//                .pageCount(request.pageCount)
//                .build();
//    }
//}
