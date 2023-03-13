package com.example.simplegraphql.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Ikechi Ucheagwu
 * @created 10/03/2023 - 02:52
 * @project SImpleGraphQL
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String name;
    private int pageCount;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Author authorDetails;
}
