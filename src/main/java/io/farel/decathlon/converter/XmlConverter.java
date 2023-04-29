package io.farel.decathlon.converter;

import io.farel.decathlon.model.AthleteTotalResult;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@RequiredArgsConstructor
public class XmlConverter {
    private static final String RESULTS_KEY = "results";
    private static final String ATHLETE_KEY = "athlete";
    private static final String NAME_KEY = "name";
    private static final String RANK_KEY = "rank";
    private static final String SCORE_KEY = "score";

    public Document convert(List<AthleteTotalResult> results) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(RESULTS_KEY);
            doc.appendChild(rootElement);

            for (AthleteTotalResult result : results) {
                Element athlete = doc.createElement(ATHLETE_KEY);
                rootElement.appendChild(athlete);
                athlete.appendChild(createElement(doc, NAME_KEY, result.getName()));
                athlete.appendChild(createElement(doc, RANK_KEY, result.getRanking().getValue()));
                athlete.appendChild(createElement(doc, SCORE_KEY, result.getScore().toString()));
            }
            return doc;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(String.format("Failed to build an xml file. %s", e.getMessage()));
        }
    }

    private Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
