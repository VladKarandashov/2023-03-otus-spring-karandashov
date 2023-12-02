package ru.otus.hw15integrationapp.util;

import com.github.javafaker.Faker;


public class Util {

    private static final Faker FAKER = Faker.instance();

    public static Faker faker(){
        return FAKER;
    }

}