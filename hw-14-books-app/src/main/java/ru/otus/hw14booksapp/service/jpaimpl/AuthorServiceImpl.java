package ru.otus.hw14booksapp.service.jpaimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw14booksapp.entity.Author;
import ru.otus.hw14booksapp.exception.NotFoundException;
import ru.otus.hw14booksapp.repository.jpa.AuthorRepository;
import ru.otus.hw14booksapp.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_EXIST));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAllBy().stream()
                .map(authorJpa -> (Author) authorJpa)
                .collect(Collectors.toList());
    }

}