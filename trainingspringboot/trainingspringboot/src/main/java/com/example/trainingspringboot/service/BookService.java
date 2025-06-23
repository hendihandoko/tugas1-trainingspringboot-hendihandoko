package com.example.trainingspringboot.service;

import com.example.trainingspringboot.entity.Book;
import com.example.trainingspringboot.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long currentId = 1L;
    public List<Book> getAllBooks() {
        return books;
    }
    public Book addBook(Book book) {
        book.setId(currentId++);
        books.add(book);
        return book;
    }
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}