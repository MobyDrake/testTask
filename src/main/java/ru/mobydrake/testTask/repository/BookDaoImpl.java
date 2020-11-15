package ru.mobydrake.testTask.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mobydrake.testTask.entity.Book;
import ru.mobydrake.testTask.mapper.BookMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao{

    private final DataSource dataSource;
    private final BookMapper mapper;

    private JdbcTemplate jdbcTemplate;

    private final String SQL_WHERE_ID = "SELECT * FROM Book WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM Book";
    private final String SQL_CREATE_BOOK = "INSERT INTO Book (title, author, description) VALUES (?, ?, ?)";

    @PostConstruct
    private void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book findBookById(Long id) {
        return jdbcTemplate.queryForObject(SQL_WHERE_ID, mapper, id);
    }

    @Override
    public List<Book> findAllBook() {
        return jdbcTemplate.query(SQL_SELECT_ALL, mapper);
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update(SQL_CREATE_BOOK, book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
