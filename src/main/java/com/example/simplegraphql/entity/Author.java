package com.example.simplegraphql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 10/03/2023 - 02:52
 * @project SImpleGraphQL
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;
    private String firstName;
    private String lastName;
    private int age;
    @OneToMany(mappedBy = "authorDetails")
    private List<Book> bookList;
}
