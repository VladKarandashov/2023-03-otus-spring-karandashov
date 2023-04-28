package ru.otus.hw05booksapp.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05booksapp.dao.AuthorDao;
import ru.otus.hw05booksapp.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class JdbcAuthorDao implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public JdbcAuthorDao(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author findById(long id) {
        var params = Map.of("id", id);
        return jdbc.queryForObject(
                "SELECT author_id, name FROM author WHERE author_id = :id", params, new AuthorMapper());
    }

    @Override
    public Author findByName(String name) {
        var params = Map.of("name", name);
        return jdbc.queryForObject("SELECT author_id, name FROM author WHERE name = :name", params, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int id = resultSet.getInt("author_id");
            String title = resultSet.getString("name");
            return new Author(id, title);
        }
    }
}
