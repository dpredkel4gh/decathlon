package io.farel.decathlon.model;

import lombok.Value;

@Value
public class Discipline {
    DisciplineKey key;
    float a;
    float b;
    float c;
    DisciplineType type;
    DisciplineUnit disciplineUnit;
}
