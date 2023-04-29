package io.farel.decathlon.dto;

import io.farel.decathlon.model.DisciplineKey;
import lombok.Value;

@Value
public class AthleteDisciplinePerformance {
    DisciplineKey disciplineKey;
    float value;
}
