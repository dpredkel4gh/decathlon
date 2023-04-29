package io.farel.decathlon.calculator;

import io.farel.decathlon.model.Discipline;
import io.farel.decathlon.model.DisciplineType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DisciplineScoreCalculator {

    private final Map<DisciplineType, BiFunction<Float, Discipline, Integer>> calculations = new HashMap<>() {{
        put(DisciplineType.FIELD, (p, d) -> (int) Math.floor(d.getA() * Math.pow(p - d.getB(), d.getC())));
        put(DisciplineType.TRACK, (p, d) -> (int) Math.floor(d.getA() * Math.pow(d.getB() - p, d.getC())));
    }};

    public Integer calculateDisciplineScore(Discipline discipline, float performance) {
        return calculations.get(discipline.getType()).apply(performance, discipline);
    }
}
