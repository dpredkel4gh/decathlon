package io.farel.decathlon.reader;

import io.farel.decathlon.dto.AthleteTotalPerformance;

import java.nio.file.Path;
import java.util.List;

public interface Reader {
    List<AthleteTotalPerformance> read(Path path);
}
