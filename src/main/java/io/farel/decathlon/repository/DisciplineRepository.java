package io.farel.decathlon.repository;

import io.farel.decathlon.model.Discipline;
import io.farel.decathlon.model.DisciplineKey;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.farel.decathlon.model.DisciplineKey.*;
import static io.farel.decathlon.model.DisciplineType.*;
import static io.farel.decathlon.model.DisciplineUnit.*;

public class DisciplineRepository {

    public Map<DisciplineKey, Discipline> getAllAsMap() {
        return this.getAll().stream()
                .collect(Collectors.toMap(Discipline::getKey, d -> d));
    }

    public List<Discipline> getAll() {
        return Arrays.asList(
                new Discipline(HUNDRED_METRES, 25.4347f, 18f, 1.81f, TRACK, SECONDS),
                new Discipline(LONG_JUMP, 0.14354f, 220f, 1.4f, FIELD, CENTIMETRES),
                new Discipline(SHOT_PUT, 51.39f, 1.5f, 1.05f, FIELD, METRES),
                new Discipline(HIGH_JUMP, 0.8465f, 75f, 1.42f, FIELD, CENTIMETRES),
                new Discipline(FOUR_HUNDRED_METRES, 1.53775f, 82f, 1.81f, TRACK, SECONDS),
                new Discipline(ONE_HUNDRED_AND_TEN_METRES_HURDLES, 5.74352f, 28.5f, 1.92f, TRACK, SECONDS),
                new Discipline(DISCUS_THROW, 12.91f, 4f, 1.1f, FIELD, METRES),
                new Discipline(POLE_VAULT, 0.2797f, 100f, 1.35f, FIELD, CENTIMETRES),
                new Discipline(JAVELIN_THROW, 10.14f, 7f, 1.08f, FIELD, METRES),
                new Discipline(FIFTEEN_HUNDRED_METRES, 0.03768f, 480f, 1.85f, TRACK, SECONDS));
    }
}
