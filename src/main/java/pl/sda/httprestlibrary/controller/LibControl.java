package pl.sda.httprestlibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.httprestlibrary.model.Book;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
public class LibControl {
    private final List<Book> bookList = new LinkedList<>();

    private boolean checkIfAlreadyAdded(Object o) {
        if (o instanceof Book) {
            Book book = (Book) o;
            for (Book entry : bookList) {
                if (entry.getName().equals(book.getName())
                        && entry.getAuthor().getFirstname().equals(book.getAuthor().getFirstname())
                        && entry.getAuthor().getLasttname().equals(book.getAuthor().getLasttname())) {
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping()
    ResponseEntity<List<Book>> getAllBooks(HttpServletResponse response) {
//        Author author = new Author();
//        Book book = new Book();
//        author.setFirstname("A");
//        author.setLasttname("B");
//        book.setAuthor(author);
//        book.setName("kniga");
//        book.setAvailable(true);
//        bookList.add(book);

        response.addHeader("Arek", "Sekula");
        return new ResponseEntity<>(bookList, HttpStatus.I_AM_A_TEAPOT);
    }

    @PutMapping
    ResponseEntity<HttpStatus> addBook(@RequestBody Book book) {
        if (!checkIfAlreadyAdded(book)) {
            bookList.add(book);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }
}
