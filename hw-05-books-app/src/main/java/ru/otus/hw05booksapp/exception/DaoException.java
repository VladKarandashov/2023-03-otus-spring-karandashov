package ru.otus.hw05booksapp.exception;

public class DaoException extends RuntimeException {

    public DaoException(String reason, Exception e) {
        super(reason);
    }

    public DaoException(String reason) {
        super(reason);
    }
}