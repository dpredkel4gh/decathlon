package io.farel.decathlon;

import io.farel.decathlon.calculator.DisciplineScoreCalculator;
import io.farel.decathlon.calculator.TotalScoreCalculator;
import io.farel.decathlon.converter.CsvConverter;
import io.farel.decathlon.converter.XmlConverter;
import io.farel.decathlon.model.Pair;
import io.farel.decathlon.reader.CsvReader;
import io.farel.decathlon.reader.ReaderFacade;
import io.farel.decathlon.repository.DisciplineRepository;
import io.farel.decathlon.service.DecathlonService;
import io.farel.decathlon.service.RankingService;
import io.farel.decathlon.util.UnitConverter;
import io.farel.decathlon.writer.WriterFacade;
import io.farel.decathlon.writer.XmlWriter;

public class Application {

    public static void main(String[] args) {
        Pair<String, String> filePaths = getFilePaths(args);
        UnitConverter unitConverter = new UnitConverter();
        DisciplineRepository disciplineRepository = new DisciplineRepository();
        CsvConverter csvConverter = new CsvConverter(unitConverter, disciplineRepository);
        CsvReader csvReader = new CsvReader(csvConverter);
        ReaderFacade readerFacade = new ReaderFacade(csvReader);
        DisciplineScoreCalculator disciplineScoreCalculator = new DisciplineScoreCalculator();
        TotalScoreCalculator totalScoreCalculator = new TotalScoreCalculator(disciplineRepository, disciplineScoreCalculator);
        RankingService rankingService = new RankingService();
        XmlConverter xmlConverter = new XmlConverter();
        XmlWriter xmlWriter = new XmlWriter(xmlConverter);
        WriterFacade writerFacade = new WriterFacade(xmlWriter);
        DecathlonService decathlonService = new DecathlonService(readerFacade, totalScoreCalculator, rankingService, writerFacade);

        decathlonService.decathlon(filePaths.getLeft(), filePaths.getRight());
    }

    private static Pair<String, String> getFilePaths(String[] args) {
        if (args.length >= 2) {
            return Pair.of(args[0], args[1]);
        }
        String input = "src/main/resources/results.csv";
        String output = "src/main/resources/results.xml";
        return Pair.of(input, output);
    }
}
