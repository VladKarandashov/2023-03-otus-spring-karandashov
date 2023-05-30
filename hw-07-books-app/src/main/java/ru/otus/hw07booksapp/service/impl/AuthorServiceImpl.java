package ru.otus.hw07booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw07booksapp.entity.Author;
import ru.otus.hw07booksapp.exception.NotFoundException;
import ru.otus.hw07booksapp.repository.AuthorRepository;
import ru.otus.hw07booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public long update(String fullName) {
        Author author = new Author(null, fullName);
        return authorRepository.save(author).getId();
    }

    @Transactional
    @Override
    public void update(long id, String fullName) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
        author.setName(fullName);
        authorRepository.save(author);
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
        return authorRepository.findAllBy();
    }

    @Transactional
    @Override
    public void deleteById(long authorId) {
        authorRepository.deleteById(authorId);
    }
}