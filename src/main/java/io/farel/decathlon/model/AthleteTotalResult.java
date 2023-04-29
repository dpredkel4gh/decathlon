package io.farel.decathlon.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AthleteTotalResult {
    String name;
    Integer score;
    Rank ranking;
}
