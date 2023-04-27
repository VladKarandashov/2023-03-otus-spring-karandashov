package ru.otus.hw05booksapp.dao.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw05booksapp.dao.BookDao;
import ru.otus.hw05booksapp.entity.Author;
import ru.otus.hw05booksapp.entity.Book;
import ru.otus.hw05booksapp.entity.Genre;
import ru.otus.hw05booksapp.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int getCount() {
        var count = jdbc.getJdbcOperations().queryForObject("SELECT count(*) FROM book ", Integer.class);
        return (count == null) ? 0 : count;
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("SELECT b.id id, " +
                        " b.author_id author_id, " +
                        " b.genre_id genre_id, " +
                        " b.title book_title, " +
                        " a.name author_name, " +
                        " g.title genre_title " +
                        " FROM book b " +
                        " INNER JOIN author a ON a.id = b.author_id " +
                        " INNER JOIN genre  g ON b.genre_id = g.id ",
                new BookMapper());
    }

    @Override
    public Book findById(long bookId) {
        var params = Map.of("book_id", bookId);
        return jdbc.queryForObject("SELECT b.id id, " +
                " b.author_id author_id, " +
                " b.genre_id genre_id, " +
                " b.title book_title, " +
                " a.name author_name, " +
                " g.title genre_title " +
                " FROM book b" +
                " INNER JOIN author a ON a.id = b.author_id " +
                " INNER JOIN genre  g ON b.genre_id = g.id " +
                " WHERE b.id = :book_id ", params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        var params = Map.of("id", id);
        jdbc.update("DELETE FROM book WHERE id = :id", params);
    }

    @Override
    public Book updateTitleById(long id, String newTitle) {
        var params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("book_title", newTitle);
        jdbc.update("UPDATE book SET title = :book_title WHERE id = :id", params);
        return findById(id);
    }

    @Override
    public Book updateById(Book book) {
        var params = new HashMap<String, Object>();
        params.put("id", book.getId());
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        params.put("book_title", book.getTitle());
        jdbc.update(
                "UPDATE book SET author_id = :author_id, genre_id = :genre_id, title = :book_title WHERE id = :id",
                params
        );
        return findById(book.getId());
    }

    @Override
    public Book insert(Book book)  {
        var params = new HashMap<String, Object>();
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        params.put("book_title", book.getTitle());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbc.update(
                    "INSERT INTO book(author_id, genre_id, title) VALUES (:author_id, :genre_id, :book_title )",
                    new MapSqlParameterSource(params), keyHolder, new String[]{"id"}
            );
        } catch (Exception e) {
            if (e.getClass().equals(DataIntegrityViolationException.class)) {
                throw new DaoException("Unexpected exception during book insertion.", e);
            } else {
                throw e;
            }
        }
        var newId = keyHolder.getKey();
        if (newId == null) {
            throw new DaoException("Unexpected exception during book insertion.");
        }
        return findById(newId.longValue());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_title"));
            return new Book(resultSet.getLong("id"), author, genre, resultSet.getString("book_title"));
        }
    }
}
