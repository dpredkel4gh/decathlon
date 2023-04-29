package io.farel.decathlon.writer;

import io.farel.decathlon.model.AthleteTotalResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.farel.decathlon.util.FileUtils.getExtension;

public class WriterFacade {
    private final Map<String, Writer> writers;

    public WriterFacade(XmlWriter xmlWriter) {
        writers = new HashMap<>();
        writers.put("xml", xmlWriter);
        //todo add csv/html/whatever
    }

    public void write(List<AthleteTotalResult> results, String filepath) {
        Writer writer = writers.get(getExtension(filepath));
        if (writer == null) {
            throw new IllegalArgumentException("unsupported output format");
        }

        writer.write(results, filepath);
    }

}
