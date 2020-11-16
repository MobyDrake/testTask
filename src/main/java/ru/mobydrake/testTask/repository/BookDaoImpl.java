package ru.mobydrake.testTask.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mobydrake.testTask.entity.Book;
import ru.mobydrake.testTask.mapper.BookMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao{

    private final JdbcTemplate jdbcTemplate;
    private final BookMapper mapper;

    @Override
    public Book findBookById(Long id) {
        final String SQL_WHERE_ID = "SELECT * FROM Book WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL_WHERE_ID, mapper, id);
    }

    @Override
    public List<Book> findAllBook() {
        final String SQL_SELECT_ALL = "SELECT * FROM Book";
        return jdbcTemplate.query(SQL_SELECT_ALL, mapper);
    }

    @Override
    public int save(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) return 0;

        final String SQL_CREATE_BOOK = "INSERT INTO Book (title, author, description) VALUES (?, ?, ?)";
        return jdbcTemplate.update(SQL_CREATE_BOOK, book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
