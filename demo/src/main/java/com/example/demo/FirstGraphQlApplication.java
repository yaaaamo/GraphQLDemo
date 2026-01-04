package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FirstGraphQlApplication {

  public static void main(String[] args) {
    SpringApplication.run(FirstGraphQlApplication.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(
          AuthorRepository authorRepository,
          BookRepository bookRepository
  ) {
    return args -> {

      Author josh = authorRepository.save(new Author(null, "josh"));
      Author mark = authorRepository.save(new Author(null, "mark"));

      Book b1 = new Book("zort", "annen", mark);
      Book b2 = new Book("31", "kaka", josh);

      bookRepository.saveAll(List.of(b1, b2));
    };
  }
}
