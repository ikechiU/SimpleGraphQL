//package com.example.simplegraphql.pojo;
//
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
// * @created 10/03/2023 - 02:51
// * @project SImpleGraphQL
// */
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class BookResponse {
//    private long bookId;
//    private String name;
//    private int pageCount;
//    private long authorId;
//
//    public static BookResponse mapFromBook(Book book) {
//        return BookResponse.builder()
//                .bookId(book.getBookId())
//                .name(book.getName())
//                .pageCount(book.getPageCount())
//                .authorId(book.getAuthorDetails().getAuthorId())
//                .build();
//    }
//
//    public static List<BookResponse> mapFromBook(List<Book> bookList) {
//        return bookList.stream()
//                .map(BookResponse::mapFromBook)
//                .collect(Collectors.toList());
//    }
//
//}
