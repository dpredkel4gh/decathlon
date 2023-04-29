package io.farel.decathlon.service;

import io.farel.decathlon.calculator.TotalScoreCalculator;
import io.farel.decathlon.dto.AthleteTotalPerformance;
import io.farel.decathlon.model.AthleteTotalResult;
import io.farel.decathlon.reader.ReaderFacade;
import io.farel.decathlon.writer.WriterFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DecathlonService {
    private final ReaderFacade reader;
    private final TotalScoreCalculator totalScoreCalculator;
    private final RankingService rankingService;
    private final WriterFacade writerFacade;

    public void decathlon(String input, String output) {
        List<AthleteTotalPerformance> performances = reader.read(input);
        List<AthleteTotalResult> totalScores = performances.stream()
                .map(performance ->
                        AthleteTotalResult.builder()
                                .name(performance.getName())
                                .score(totalScoreCalculator.calculateTotalScore(performance))
                                .build()
                ).collect(Collectors.toList());
        List<AthleteTotalResult> sortedResults = rankingService.rank(totalScores);
        writerFacade.write(sortedResults, output);
    }
}
