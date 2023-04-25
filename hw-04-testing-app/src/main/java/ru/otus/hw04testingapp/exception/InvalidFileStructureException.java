package ru.otus.hw04testingapp.exception;

public class InvalidFileStructureException extends RuntimeException {
    public InvalidFileStructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
