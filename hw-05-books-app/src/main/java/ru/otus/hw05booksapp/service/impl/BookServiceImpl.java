package ru.otus.hw05booksapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw05booksapp.dao.AuthorDao;
import ru.otus.hw05booksapp.dao.BookDao;
import ru.otus.hw05booksapp.dao.GenreDao;
import ru.otus.hw05booksapp.entity.Author;
import ru.otus.hw05booksapp.entity.Book;
import ru.otus.hw05booksapp.entity.Genre;
import ru.otus.hw05booksapp.exception.NotFoundException;
import ru.otus.hw05booksapp.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Override
    public int getCount() {
        return bookDao.getCount();
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateTitleById(long id, String newTitle) {
        bookDao.updateTitleById(id, newTitle);
    }

    @Override
    public Book insert(long authorId, long genreId, String bookTitle) {
        Author author = findAuthorById(authorId);
        Genre genre = findGenreById(genreId);
        Book book = new Book(bookTitle, author, genre);
        return bookDao.insert(book);
    }

    private Author findAuthorById(long authorId) {
        try {
            return authorDao.findById(authorId);
        } catch (Exception e) {
            throw new NotFoundException("Author not exist", e);
        }
    }

    private Genre findGenreById(long genreId) {
        try {
            return genreDao.findById(genreId);
        } catch (Exception e) {
            throw new NotFoundException("Genre not exist", e);
        }
    }
}
