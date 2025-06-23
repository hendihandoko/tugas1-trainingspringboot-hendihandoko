package com.example.trainingspringboot.controller;

import com.example.trainingspringboot.entity.Book;
import com.example.trainingspringboot.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBookById(id);
        return "Buku dengan ID " + id + " berhasil dihapus.";
    }
}
