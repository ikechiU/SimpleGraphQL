# An Introduction to GraphQL Web Service using Spring Boot.
### This explains the concept of GraphQL with a simple CRUD operation of GraphQL.

[![Java Build](https://img.shields.io/badge/Java-SpringBoot-brightgreen)](https://spring.io/projects/spring-boot) [![Spring JPA](https://img.shields.io/badge/Spring-JPA-blue)](https://spring.io/projects/spring-data-jpa) [![Postgres](https://img.shields.io/badge/Postgres-%20SQL-blue)](https://www.postgresql.org/)

## Overview
GraphQL is a query language to retrieve data from a server. It is an alternative to REST, SOAP or gRPC.

## Why GraphQL?
Traditional REST APIs work with the concept of Resources that the server manages. We can manipulate these resources in 
some standard ways, following the various HTTP verbs. This works very well as long as our API fits the resource concept 
but quickly falls apart when we need to deviate from it. This also suffers when the client needs data from multiple 
resources simultaneously, such as requesting a blog post and comments. Typically, this is solved by having the 
client make multiple requests or having the server supply extra data that might not always be required, leading to larger response sizes.

GraphQL offers a solution to both of these problems. It allows the client to specify exactly what data it desires, including navigating child resources in a single request and allows for multiple queries in a single request.


## Features of GraphQL
One important property of GraphQL is that it is statically typed the server knows exactly the shape of 
every object you query and any client can actually introspect the server and ask for the "schema". The 
schema here refers to GraphQl schema that describes what queries are possible and what fields you can get back.
The GraphQL schema has a language which is consistent across languages. This means that if you are using Java, 
JavaScript or Go you will understand a GraphQL schema because it is language agnostic.

N/B: GraphQL accepts only one root query and one root mutation types. So the root types will inherit the root Query and mutation type.

The schema for this project is located in: **`resources/graphql`** which is the default path to enable spring locate the schema.
Then, the schema are named with the extension **.graphqls**, in this project we have two schemas **`author.graphqls`** and **`book.graphqls`**. 


**`author.graphqls`** schema

        type Author {
            authorId: ID!
            firstName: String!
            lastName: String!
            age: Int
        }

        #Root
        type Query {
            findAllAuthors: [Author]!
            findAllAuthorsPaged(page: Int, limit: Int): [Author]!
            countAuthors: Int!
            findAuthorById(authorId: ID): Author
        }

        #Root
        type Mutation {
            createAuthor(firstName: String!, lastName: String!, age: Int!): Author
        }


**`book.graphqls`** schema
        
        type Book {
            bookId: ID!
            name: String!
            pageCount: Int!
            author: Author
        }
        
        extend type Query {
            findAllBooks: [Book]!
            findAllBooksPaged(page: Int, limit: Int): [Book]!
            countBooks: Int!
            findBookById(bookId: ID): Book
        }
        
        extend type Mutation {
            createBook(name: String!, pageCount: Int!, authorId: Int!): Book
            updateBook(bookId: Int!, name: String!, pageCount: Int!): Book
            deleteBook(bookId: ID!): Boolean
        }



* Object Types and Fields

Object types and fields are a common type that you will work with in any GraphQL Schema. Once you have your models in place in your Spring application you will need to define what objects and fields you want to make available in your API. The following is a schema definition for a Book object:
        
        type Book {
            bookId: ID!
            name: String!
            pageCount: Int!
            author: Author
        }


GraphQL comes with the following Scalar types:

- Int: A signed 32-bit integer.
- Float: A signed double-precision floating-point value.
- String: A UTF-8 character sequence.
- Boolean: true or false.
- ID: The ID scalar type represents a unique identifier, often used  to fetch an object or as the key for a cache. The ID type is serialized in the same way as a String; however, defining it as an ID signifies that it is not intended to be human-readable.

* Mutation: Used to create, update and delete data. It can also read data; so we can also say it is a read-write operation requested to a GraphQL server

        type Mutation {
            createAuthor(firstName: String!, lastName: String!, age: Int!): Author
        }

Here is an example on for this project that would create a Book on Postman: the fields after the createBook is a response we want to return when the Book is created.
      
      mutation {
          createBook(
              name: "The Joys of Motherhood",
              pageCount: 105,
              authorId: 102
          ) {
            bookId
            name
            pageCount
            author {
                authorId
                firstName
                lastName
                age
            }
          }
      }


* Query: Used to read data

        type Query {
             findAllAuthors: [Author]!
             findAllAuthorsPaged(page: Int, limit: Int): [Author]!
             countAuthors: Int!
             findAuthorById(authorId: ID!): Author
        }


* Arguments: Each field on a GraphQL object type can have one or more arguments. The **`findAuthorById`** query above defines a single argument of type ID. The ! states that the ID can’t be null and must be supplied. When you write a query against that you can pass in the id like so:

       query {
            findAuthorById(authorId: 1) {
                 authorId
                 firstName
                 lastName
                 age
            }
       }
When this is supplied on Postman to fetch an Author the result will be like this below:
        
        {
            "data": {
                "findAuthorById": {
                    "authorId": "1",
                    "firstNae": "Stephen",
                    "lastName": "King",
                    "age": 86
                }
            }
        }

* GraphQL Java is the server side execution engine it is only concerned with executing queries of GraphQL in Java. 
It is an implementation based on the specification and the JavaScript reference implementation. Note that it requires at least Java 8 to run properly. 


* GraphQL Spring Boot Starter: Offers a fantastic way to get a GraphQL server running in a very short time. Using autoconfiguration and an annotation-based programming approach, we need only write the code necessary for our service.
        
        <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-graphql</artifactId>
        </dependency>


## A sample Controller
      @Controller
      @RequiredArgsConstructor
      public class BookController {
      
          private final BookQueryServiceImpl bookQueryService;
          private final BookMutationServiceImpl bookMutationService;
          private final BookResolverImpl bookResolver;
      
          @QueryMapping
          public Book findBookById(@Argument int bookId) {
              return bookQueryService.findBookById(bookId);
          }          

          @QueryMapping
          public List<Book> findAllBooksPaged(@Argument int page, @Argument int limit) {
              return bookQueryService.findAllBooksPaged(page, limit);
          }
      
          @SchemaMapping
          public Author author(Book book) {
              return bookResolver.getAuthor(book);
          }
      }

* The `**@QueryMapping**` annotation binds this method to a query, a field under the Query type. The query field is 
then determined from the method name, findBookById. It could also be declared on the annotation. 
Spring for GraphQL uses RuntimeWiring.Builder to register the handler method as a graphql.schema.DataFetcher for the query field findBookById.

In GraphQL Java, DataFetchingEnvironment provides access to a map of field-specific argument values. 
Use the **`@Argument`** annotation to have an argument bound to a target object and injected into the handler method. 
By default, the method parameter name is used to look up the argument. The argument name can be specified in the annotation.

The `**@SchemaMapping**` annotation maps a handler method to a field in the GraphQL schema and declares it to be the 
DataFetcher for that field. The field name defaults to the method name, and the type name defaults to the simple class
name of the source/parent object injected into the method. In this example, the field defaults to author and the type
defaults to Book. The type and field can be specified in the annotation.

Finally, in the service class implementation where we implemented the Query Resolver every field in the schema root query 
have a method with the same name. This is important. 


## Sources
- https://www.baeldung.com/graphql
- https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/
- https://www.baeldung.com/spring-graphql
- https://www.bezkoder.com/spring-boot-graphql-mysql-jpa/

To know more: 
- https://www.danvega.dev/blog/2022/05/17/spring-for-graphql/
- https://blog.devgenius.io/graphql-with-java-and-spring-boot-b64d3ce427a2

## Other projects
https://github.com/ikechiU?tab=repositories

## Author
Ikechi Ucheagwu 