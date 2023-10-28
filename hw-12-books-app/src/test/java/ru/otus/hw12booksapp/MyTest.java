package ru.otus.hw12booksapp;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyTest {

    @Test
    void name() {
        var encoder = new BCryptPasswordEncoder();
        System.out.println("pass: "+encoder.encode("1234"));
    }
}
