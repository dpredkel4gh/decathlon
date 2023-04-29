package io.farel.decathlon.calculator;

import io.farel.decathlon.model.Discipline;
import io.farel.decathlon.model.DisciplineKey;
import io.farel.decathlon.repository.DisciplineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineScoreCalculatorTest {
    private DisciplineScoreCalculator subject;

    private Map<DisciplineKey, Discipline> disciplines;

    @BeforeEach
    void setUp() {
        subject = new DisciplineScoreCalculator();

        DisciplineRepository disciplineRepository = new DisciplineRepository();
        disciplines = disciplineRepository.getAllAsMap();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/discipline_calculator_test_values.csv", numLinesToSkip = 1)
    void calculateDisciplineScore(DisciplineKey disciplineKey, float performance, int score) {
        Discipline discipline = disciplines.get(disciplineKey);

        Integer actualScore = subject.calculateDisciplineScore(discipline, performance);

        assertEquals(score, actualScore);
    }
}