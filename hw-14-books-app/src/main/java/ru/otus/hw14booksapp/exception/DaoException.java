package ru.otus.hw14booksapp.exception;

public class DaoException extends RuntimeException {

    public DaoException(String reason, Exception e) {
        super(reason, e);
    }

    public DaoException(String reason) {
        super(reason);
    }
}