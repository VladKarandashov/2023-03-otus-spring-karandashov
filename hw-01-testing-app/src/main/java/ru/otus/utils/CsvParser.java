package ru.otus.utils;

import java.io.InputStream;
import java.util.List;

public interface CsvParser {
    List<String> readLines(InputStream inputStream);

    List<List<String>> readLinesItems(InputStream inputStream, String separator);

    List<List<String>> readLinesItems(InputStream inputStream, String separator, boolean isHead);
}
