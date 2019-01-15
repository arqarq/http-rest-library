package pl.sda.httprestlibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.httprestlibrary.model.Book;

import java.util.LinkedList;
import java.util.List;

@RestController
public class LibControl {
    private List<Book> bookList = new LinkedList<>();

    @GetMapping
    ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
