package ru.otus.hw07booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07booksapp.entity.Genre;
import ru.otus.hw07booksapp.exception.NotFoundException;
import ru.otus.hw07booksapp.repository.GenreRepository;
import ru.otus.hw07booksapp.service.GenreService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private static final String GENRE_NOT_EXIST = "Didn't find genre";

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public long create(String name) {
        Genre genre = new Genre(null, name);
        return genreRepository.save(genre).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAllBy();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
    }

    @Transactional
    @Override
    public void update(long id, String name) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_EXIST));
        genre.setName(name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(long genreId) {
        genreRepository.deleteById(genreId);
    }
}