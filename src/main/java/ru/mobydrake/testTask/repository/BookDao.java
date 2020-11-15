package ru.mobydrake.testTask.repository;

import ru.mobydrake.testTask.entity.Book;

import java.util.List;

public interface BookDao {

    Book findBookById(Long id);

    List<Book> findAllBook();

    int save(Book book);


}
