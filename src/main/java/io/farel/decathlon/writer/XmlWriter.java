package io.farel.decathlon.writer;

import io.farel.decathlon.converter.XmlConverter;
import io.farel.decathlon.model.AthleteTotalResult;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.List;

@RequiredArgsConstructor
public class XmlWriter implements Writer {

    private final XmlConverter xmlConverter;

    @Override
    public void write(List<AthleteTotalResult> results, String path) {
        Document document = xmlConverter.convert(results);
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.transform(source, new StreamResult(new FileOutputStream(path)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to save an xml file. %s", e.getMessage()));
        }
    }

}
