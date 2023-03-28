package ru.otus.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvParserImpl implements CsvParser {

    public List<String> readLines(InputStream inputStream) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public List<List<String>> readLinesItems(InputStream inputStream, String separator) {
        var lines = readLines(inputStream);
        return lines.stream()
                .map(line -> line.split(separator))
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    public List<List<String>> readLinesItems(InputStream inputStream, String separator, boolean isHaveHead) {
        int countSkipItems = isHaveHead ? 1 : 0;
        return readLinesItems(inputStream, separator).stream()
                .skip(countSkipItems)
                .collect(Collectors.toList());
    }
}
