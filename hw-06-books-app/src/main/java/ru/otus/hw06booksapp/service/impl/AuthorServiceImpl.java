package ru.otus.hw06booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06booksapp.entity.Author;
import ru.otus.hw06booksapp.exception.NotFoundException;
import ru.otus.hw06booksapp.repository.AuthorRepository;
import ru.otus.hw06booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public long create(String fullName) {
        Author author = new Author(null, fullName);
        return authorRepository.save(author).getId();
    }

    @Transactional
    @Override
    public void update(long id, String fullName) {
        Author author = authorRepository.getAuthorById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
        author.setName(fullName);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        return authorRepository.getAuthorById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.getAllAuthors();
    }

    @Transactional
    @Override
    public void delete(long authorId) {
        authorRepository.getAuthorById(authorId).ifPresent(authorRepository::delete);
    }
}