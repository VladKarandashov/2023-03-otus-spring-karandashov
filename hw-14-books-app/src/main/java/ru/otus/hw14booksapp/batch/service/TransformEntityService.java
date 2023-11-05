package ru.otus.hw14booksapp.batch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.hw14booksapp.entity.jpa.AuthorJpa;
import ru.otus.hw14booksapp.entity.mongo.AuthorMongo;

@Slf4j
@Service
public class TransformEntityService implements ItemProcessor<AuthorJpa, AuthorMongo> {
    @Override
    public AuthorMongo process(AuthorJpa authorJpa) {
        String id = String.valueOf(authorJpa.getId());
        String name = authorJpa.getName();
        return new AuthorMongo(id, name);
    }
}
