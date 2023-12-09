package ru.otus.hw17booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw17booksapp.entity.Author;
import ru.otus.hw17booksapp.repository.AuthorRepository;
import ru.otus.hw17booksapp.service.AuthorService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}