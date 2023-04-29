package io.farel.decathlon.dto;

import lombok.Value;

import java.util.List;

@Value
public class AthleteTotalPerformance {
    String name;
    List<AthleteDisciplinePerformance> performances;
}
