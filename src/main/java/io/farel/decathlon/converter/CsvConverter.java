package io.farel.decathlon.converter;

import io.farel.decathlon.dto.AthleteDisciplinePerformance;
import io.farel.decathlon.dto.AthleteTotalPerformance;
import io.farel.decathlon.dto.csv.DisciplineShowingCsvMapping;
import io.farel.decathlon.model.Discipline;
import io.farel.decathlon.model.DisciplineKey;
import io.farel.decathlon.repository.DisciplineRepository;
import io.farel.decathlon.util.UnitConverter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvConverter {

    private final UnitConverter unitConverter;
    private final DisciplineRepository disciplineRepository;

    public AthleteTotalPerformance convert(String[] values) {
        Map<DisciplineKey, Discipline> disciplineMap = disciplineRepository.getAllAsMap();

        List<AthleteDisciplinePerformance> athleteShowings = Arrays.stream(DisciplineShowingCsvMapping.values())
                .map(mapping ->
                        new AthleteDisciplinePerformance(
                                mapping.getKey(),
                                unitConverter.convert(
                                        values[mapping.getPosition()],
                                        mapping.getUnit(),
                                        disciplineMap.get(mapping.getKey()).getDisciplineUnit())
                        )
                ).collect(Collectors.toList());

        return new AthleteTotalPerformance(values[0], athleteShowings);
    }
}
