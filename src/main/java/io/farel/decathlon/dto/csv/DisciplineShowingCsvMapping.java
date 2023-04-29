package io.farel.decathlon.dto.csv;

import io.farel.decathlon.model.DisciplineKey;
import io.farel.decathlon.model.DisciplineUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static io.farel.decathlon.model.DisciplineKey.*;
import static io.farel.decathlon.model.DisciplineUnit.*;

@Getter
@RequiredArgsConstructor
public enum DisciplineShowingCsvMapping {
    KEY_HUNDRED_METRES(HUNDRED_METRES, 1, SECONDS),
    KEY_LONG_JUMP(LONG_JUMP, 2, METRES),
    KEY_SHOT_PUT(SHOT_PUT, 3, METRES),
    KEY_HIGH_JUMP(HIGH_JUMP, 4, METRES),
    KEY_FOUR_HUNDRED_METRES(FOUR_HUNDRED_METRES, 5, SECONDS),
    KEY_ONE_HUNDRED_AND_TEN_METRES_HURDLES(ONE_HUNDRED_AND_TEN_METRES_HURDLES, 6, SECONDS),
    KEY_DISCUS_THROW(DISCUS_THROW, 7, METRES),
    KEY_POLE_VAULT(POLE_VAULT, 8, METRES),
    KEY_JAVELIN_THROW(JAVELIN_THROW, 9, METRES),
    KEY_FIFTEEN_HUNDRED_METRES(FIFTEEN_HUNDRED_METRES, 10, MINUTES);

    private final DisciplineKey key;
    private final int position;
    private final DisciplineUnit unit;

}
