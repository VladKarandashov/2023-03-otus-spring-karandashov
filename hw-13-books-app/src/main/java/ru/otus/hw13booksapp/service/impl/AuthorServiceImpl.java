package ru.otus.hw13booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw13booksapp.entity.Author;
import ru.otus.hw13booksapp.exception.NotFoundException;
import ru.otus.hw13booksapp.repository.AuthorRepository;
import ru.otus.hw13booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Author create(String fullName) {
        Author author = new Author(null, fullName);
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long authorId) {
        authorRepository.deleteById(authorId);
    }
}