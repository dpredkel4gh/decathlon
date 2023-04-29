package io.farel.decathlon.model;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class Rank {
    String value;

    public static Rank of(List<Integer> ranks) {
        String rank = ranks.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-"));
        return new Rank(rank);
    }
}
