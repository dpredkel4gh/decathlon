package io.farel.decathlon.calculator;

import io.farel.decathlon.dto.AthleteTotalPerformance;
import io.farel.decathlon.model.Discipline;
import io.farel.decathlon.model.DisciplineKey;
import io.farel.decathlon.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class TotalScoreCalculator {
    private final DisciplineRepository disciplineRepository;
    private final DisciplineScoreCalculator disciplineScoreCalculator;

    public Integer calculateTotalScore(AthleteTotalPerformance totalShowing) {
        Map<DisciplineKey, Discipline> allAsMap = disciplineRepository.getAllAsMap();
        return totalShowing.getPerformances().stream()
                .map(performance -> {
                    Discipline discipline = allAsMap.get(performance.getDisciplineKey());
                    return disciplineScoreCalculator.calculateDisciplineScore(discipline, performance.getValue());
                })
                .reduce(0, Integer::sum);
    }
}
