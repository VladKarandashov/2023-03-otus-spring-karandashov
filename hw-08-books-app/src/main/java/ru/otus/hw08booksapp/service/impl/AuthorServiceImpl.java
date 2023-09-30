package ru.otus.hw08booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08booksapp.entity.Author;
import ru.otus.hw08booksapp.exception.NotFoundException;
import ru.otus.hw08booksapp.repository.AuthorRepository;
import ru.otus.hw08booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public String create(String fullName) {
        Author author = new Author(null, fullName);
        return authorRepository.save(author).getId();
    }

    @Transactional
    @Override
    public void update(String id, String fullName) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
        author.setName(fullName);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getById(String id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAllBy();
    }

    @Transactional
    @Override
    public void deleteById(String authorId) {
        authorRepository.deleteById(authorId);
    }
}