package ru.otus.hw05booksapp.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05booksapp.dao.GenreDao;
import ru.otus.hw05booksapp.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre findById(long id) {
        var params = Map.of("id", id);
        return jdbc.queryForObject("SELECT id, title FROM genre WHERE id = :id", params, new GenreMapper());
    }

    @Override
    public Genre findByTitle(String title) {
        var params = Map.of("title", title);
        return jdbc.queryForObject("SELECT id, title FROM genre WHERE title = :title", params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            return new Genre(id, title);
        }
    }
}
