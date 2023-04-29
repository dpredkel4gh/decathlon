package io.farel.decathlon.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DisciplineKey {
    HUNDRED_METRES("100m"),
    LONG_JUMP("long jump"),
    SHOT_PUT("shot put"),
    HIGH_JUMP("high jump"),
    FOUR_HUNDRED_METRES("400m"),
    ONE_HUNDRED_AND_TEN_METRES_HURDLES("110m hurdles"),
    DISCUS_THROW("discus throw"),
    POLE_VAULT("pole vault"),
    JAVELIN_THROW("javelin throw"),
    FIFTEEN_HUNDRED_METRES("1500m");

    private final String name;
}
