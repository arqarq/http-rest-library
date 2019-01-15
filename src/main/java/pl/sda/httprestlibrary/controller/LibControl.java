package pl.sda.httprestlibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.httprestlibrary.model.Book;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
public class LibControl {
    private List<Book> bookList = new LinkedList<>();

    @GetMapping
    ResponseEntity<?> getAllBooks(HttpServletResponse response) {
        response.addHeader("Arek", "Sekuła");
        return new ResponseEntity<>(bookList, HttpStatus.I_AM_A_TEAPOT);
    }
}
