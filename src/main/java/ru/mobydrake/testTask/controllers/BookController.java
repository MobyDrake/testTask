package ru.mobydrake.testTask.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mobydrake.testTask.entity.Book;
import ru.mobydrake.testTask.repository.BookDao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookDao bookDao;

    @GetMapping()
    public ResponseEntity<List<Book>> readAllBooks() {
        return ResponseEntity.ok(bookDao.findAllBook().stream()
                .sorted((o1, o2) -> o2.getTitle().compareToIgnoreCase(o1.getTitle()))
                .collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        return bookDao.save(book) > 0 ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/authors")
    public ResponseEntity<Map<String, List<Book>>> readAllBookGroupAuthor() {
        return ResponseEntity.ok(bookDao.findAllBook().stream().collect(Collectors.groupingBy(Book::getAuthor)));
    }
}
