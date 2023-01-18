package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Object> getAllBooks() {
        try {
            List<Book> topBooks = bookRepository.findTop3ByOrderByTransactionDetailAsc();
            List<Book> books = bookRepository.findAll();

            HashMap<String, Object> bookMap = new HashMap<>();
            bookMap.put("books", books);
            bookMap.put("topBooks", topBooks);

            return new ResponseEntity<>(bookMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getBook(Long id) {
        try {
            Optional<Book> data = bookRepository.findById(id);
            if (data.isPresent()) {
                Book _book = data.get();
                return new ResponseEntity<>(_book, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> add(List<Book> book) {
        try {
            // insert multiple rows
            List<Book> bookList = new ArrayList<>();
            for (Book _book : book) {
                bookList.add(new Book(
                    _book.getTitle(),
                    _book.getWriter(),
                    _book.getCategory(),
                    _book.getStock()
                ));
            }
            bookRepository.saveAll(bookList);

            return new ResponseEntity<>(bookList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> update(Long id, Book book) {
        try {
            Optional<Book> data = bookRepository.findById(id);
            if (data.isPresent()) {
                Book _book = data.get();
                _book.setTitle(book.getTitle());
                _book.setWriter(book.getWriter());
                _book.setCategory(book.getCategory());
                _book.setStock(book.getStock());
                return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        try {
            boolean _book = bookRepository.existsById(id);
            if (_book) {
                bookRepository.deleteById(id);
                return new ResponseEntity<>("Book deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
