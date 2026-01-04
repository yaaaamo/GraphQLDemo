package com.example.demo;

import org.springframework.data.auditing.AuditingHandler;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  //injection
  public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository){
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @QueryMapping //we can also give name
  Iterable<Author>authors(){
    return authorRepository.findAll();
  }

  @QueryMapping
  Optional<Author> authorById(@Argument Long id){
    return authorRepository.findById(id);
  }

  @MutationMapping
  Book addBook(@Argument BookInput book){
    Author author = authorRepository.findById((book.authorId)).orElseThrow(()->new IllegalArgumentException("illegal argument"));
    Book b =  new Book(book.title(),book.publisher(),author);
    return bookRepository.save(b);
  }
  record BookInput(String title, String publisher, Long authorId){

  }
}
