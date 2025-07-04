package com.example.trainingspringboot.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class Book {
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String title;

    @NotBlank(message = "Penulis tidak boleh kosong")
    private String author;

    public Book() {
    }

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
