package io.farel.decathlon.reader;

import io.farel.decathlon.dto.AthleteTotalPerformance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.farel.decathlon.util.FileUtils.getExtension;

public class ReaderFacade {
    private final Map<String, Reader> readers;

    public ReaderFacade(CsvReader csvReader) {
        readers = new HashMap<>();
        readers.put("csv", csvReader);
        //todo add xml/html/whatever
    }

    public List<AthleteTotalPerformance> read(String filepath) {
        Path path = Paths.get(filepath);
        if (!exists(path)) {
            throw new IllegalArgumentException("file not exist");
        }
        Reader reader = readers.get(getExtension(filepath));
        if (reader == null) {
            throw new IllegalArgumentException("unsupported input format");
        }
        return reader.read(path);
    }

    private boolean exists(Path path) {
        return Files.exists(path) && !Files.isDirectory(path);
    }

}
