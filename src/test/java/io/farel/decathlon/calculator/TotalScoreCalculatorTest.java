package io.farel.decathlon.calculator;

import io.farel.decathlon.dto.AthleteDisciplinePerformance;
import io.farel.decathlon.dto.AthleteTotalPerformance;
import io.farel.decathlon.model.DisciplineKey;
import io.farel.decathlon.repository.DisciplineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalScoreCalculatorTest {

    private TotalScoreCalculator subject;

    @BeforeEach
    void setUp() {
        DisciplineRepository disciplineRepository = new DisciplineRepository();
        DisciplineScoreCalculator disciplineScoreCalculator = new DisciplineScoreCalculator();

        subject = new TotalScoreCalculator(disciplineRepository, disciplineScoreCalculator);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/total_calculator_test_values.csv", numLinesToSkip = 1)
    void calculateTotalScore(float hundredMetres,
                             float longJump,
                             float shotPut,
                             float highJump,
                             float fourHundredMetres,
                             float oneHundredAndTenMetresHurdles,
                             float discusThrow,
                             float poleVault,
                             float javelinThrow,
                             float fifteenHundredMetres,
                             int expectedScore) {

        List<AthleteDisciplinePerformance> performances = List.of(
                new AthleteDisciplinePerformance(DisciplineKey.HUNDRED_METRES, hundredMetres),
                new AthleteDisciplinePerformance(DisciplineKey.LONG_JUMP, longJump),
                new AthleteDisciplinePerformance(DisciplineKey.SHOT_PUT, shotPut),
                new AthleteDisciplinePerformance(DisciplineKey.HIGH_JUMP, highJump),
                new AthleteDisciplinePerformance(DisciplineKey.FOUR_HUNDRED_METRES, fourHundredMetres),
                new AthleteDisciplinePerformance(DisciplineKey.ONE_HUNDRED_AND_TEN_METRES_HURDLES, oneHundredAndTenMetresHurdles),
                new AthleteDisciplinePerformance(DisciplineKey.DISCUS_THROW, discusThrow),
                new AthleteDisciplinePerformance(DisciplineKey.POLE_VAULT, poleVault),
                new AthleteDisciplinePerformance(DisciplineKey.JAVELIN_THROW, javelinThrow),
                new AthleteDisciplinePerformance(DisciplineKey.FIFTEEN_HUNDRED_METRES, fifteenHundredMetres)
        );

        AthleteTotalPerformance totalPerformance = new AthleteTotalPerformance("Test Name", performances);

        Integer actualResult = subject.calculateTotalScore(totalPerformance);

        assertEquals(expectedScore, actualResult);
    }
}