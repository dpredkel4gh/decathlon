package io.farel.decathlon.reader;

import io.farel.decathlon.converter.CsvConverter;
import io.farel.decathlon.dto.AthleteTotalPerformance;
import io.farel.decathlon.model.DisciplineKey;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CsvReader implements Reader {
    private static final String DELIMITER = ";";

    private final CsvConverter csvConverter;

    @Override
    public List<AthleteTotalPerformance> read(Path path) {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String[]> values = lines
                    .map(String::trim)
                    .filter(str -> !str.isBlank())
                    .map(line -> line.split(DELIMITER))
                    .collect(Collectors.toList());

            boolean invalid = values.stream()
                    .anyMatch(array -> array.length < DisciplineKey.values().length + 1); // + name

            if (invalid) {
                throw new IllegalArgumentException("the input params are invalid. some values are missing");
            }

            return values.stream()
                    .map(csvConverter::convert)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("failed to read the file");
        }
    }
}
