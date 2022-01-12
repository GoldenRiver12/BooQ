package com.example.demo.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Book;


public interface BookRepository {
	Optional<Book> findByIsbn13(String isbn) throws IOException;
	List<Book> findByKeyword(String title) throws IOException;
}
