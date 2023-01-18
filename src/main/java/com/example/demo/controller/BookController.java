package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody List<Book> book) {
        return bookService.add(book);
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return bookService.delete(id);
    }

}
