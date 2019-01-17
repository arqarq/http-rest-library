package pl.sda.httprestlibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.httprestlibrary.model.Author;
import pl.sda.httprestlibrary.model.Book;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
class LibControl {
    private final List<Book> bookList = new LinkedList<>();

    private boolean checkIfAlreadyAdded(Object o) {
        if (o instanceof Book) {
            Book book = (Book) o;
            for (Book entry : bookList) {
                if (entry.getName().equals(book.getName())
                        && entry.getAuthor().getFirstName().equals(book.getAuthor().getFirstName())
                        && entry.getAuthor().getLastName().equals(book.getAuthor().getLastName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfAlreadyAddedByTitle(String title) {
        for (Book entry : bookList) {
            if (entry.getName().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @PatchMapping("/{i}/{a}")
    ResponseEntity<String> updateStatus(@PathVariable("i") String title,
                                        @PathVariable("a") String available) {
        if (checkIfAlreadyAddedByTitle(title)) {
            switch (available) {
                case "0":
                    bookList.stream()
                            .filter(book -> book.getName().equals(title))
                            .findFirst()
                            .ifPresent(book -> book.setAvailable(false));
                    return new ResponseEntity<>("Status of book titled: \"" + title + "\" set to: unavailable.",
                            HttpStatus.OK);
                case "1":
                    bookList.stream()
                            .filter(book -> book.getName().equals(title))
                            .findFirst()
                            .ifPresent(book -> book.setAvailable(true));
                    return new ResponseEntity<>("Status of book titled: \"" + title + "\" set to: available.",
                            HttpStatus.OK);
                default:
                    return new ResponseEntity<>("Status to set not known.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No book titled: \"" + title + "\" in library.",
                HttpStatus.OK);
    }

    @PatchMapping("/{i}/2")
    ResponseEntity<String> updateAuthor(@PathVariable("i") String title,
                                        @RequestBody Author author) {
        if (checkIfAlreadyAddedByTitle(title)) {
            bookList.stream()
                    .filter(book -> book.getName().equals(title))
                    .findFirst()
                    .ifPresent(book -> book.setAuthor(author));
            return new ResponseEntity<>("Author of book titled: \"" + title + "\" has been changed.",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("No book titled: \"" + title + "\" in library.",
                HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<Book>> getAllBooks(HttpServletResponse response) {
//        Author author = new Author();
//        Book book = new Book();
//        author.setFirstName("A");
//        author.setLasttname("B");
//        book.setAuthor(author);
//        book.setName("kniga");
//        book.setAvailable(true);
//        bookList.add(book);
        response.addHeader("Arek", "Sekula");
        return new ResponseEntity<>(bookList, HttpStatus.I_AM_A_TEAPOT);
    }

    @PutMapping
    ResponseEntity<?> addBook(@RequestBody Book book) {
        if (!checkIfAlreadyAdded(book)) {
            bookList.add(book);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @DeleteMapping("/{i}")
    @ResponseBody
    String deleteBook(@PathVariable(value = "i") String title) {
        if (checkIfAlreadyAddedByTitle(title)) {
            bookList.stream()
                    .filter(book -> book.getName().equals(title))
                    .findFirst()
                    .ifPresent(bookList::remove);
            return "Book titled: \"" + title + "\" has been removed.";
        }
        return "No book titled: \"" + title + "\" in library.";
    }
}
