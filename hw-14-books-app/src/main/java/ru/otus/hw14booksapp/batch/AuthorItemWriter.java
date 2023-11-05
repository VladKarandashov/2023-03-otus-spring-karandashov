package ru.otus.hw14booksapp.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import ru.otus.hw14booksapp.entity.mongo.AuthorMongo;

public class AuthorItemWriter implements ItemWriter<AuthorMongo> {

    private final RepositoryItemWriter<AuthorMongo> repositoryItemWriter;

    public AuthorItemWriter(RepositoryItemWriter<AuthorMongo> repositoryItemWriter) {
        this.repositoryItemWriter = repositoryItemWriter;
    }

    @Override
    public void write(Chunk<? extends AuthorMongo> chunk) throws Exception {
        repositoryItemWriter.write(chunk);
    }
}