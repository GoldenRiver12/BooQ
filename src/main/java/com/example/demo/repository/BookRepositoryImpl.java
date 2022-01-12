package com.example.demo.repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;
import com.example.demo.util.GoogleBooksApi;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@Override
	public Optional<Book> findByIsbn13(String isbn) throws IOException {
		Map<String, String> queryParameters = Map.of(
				"q", "isbn:" + isbn
				, "printType", "books");
		return GoogleBooksApi.getBookListFromGba(queryParameters)
				.stream().findAny();
		
	}

	@Override
	public List<Book> findByKeyword(String keyword) throws IOException {
		Map<String, String> queryParameters = Map.of(
				"q", keyword
				, "printType", "books");
		return GoogleBooksApi.getBookListFromGba(queryParameters);
	}



}
