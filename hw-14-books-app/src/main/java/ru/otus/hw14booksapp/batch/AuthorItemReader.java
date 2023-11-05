package ru.otus.hw14booksapp.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import ru.otus.hw14booksapp.entity.jpa.AuthorJpa;

public class AuthorItemReader implements ItemReader<AuthorJpa> {

    private final RepositoryItemReader<AuthorJpa> repositoryItemReader;

    public AuthorItemReader(RepositoryItemReader<AuthorJpa> repositoryItemReader) {
        this.repositoryItemReader = repositoryItemReader;
    }

    @Override
    public AuthorJpa read() throws Exception {
        return repositoryItemReader.read();
    }
}