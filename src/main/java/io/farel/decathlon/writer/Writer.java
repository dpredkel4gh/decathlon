package io.farel.decathlon.writer;

import io.farel.decathlon.model.AthleteTotalResult;

import java.util.List;

public interface Writer {
    void write(List<AthleteTotalResult> results, String path);
}
