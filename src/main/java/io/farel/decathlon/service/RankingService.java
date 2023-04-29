package io.farel.decathlon.service;

import io.farel.decathlon.model.AthleteTotalResult;
import io.farel.decathlon.model.Rank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankingService {

    public List<AthleteTotalResult> rank(List<AthleteTotalResult> totalScores) {
        List<AthleteTotalResult> sortedScores = new ArrayList<>(totalScores);
        sortedScores.sort(Comparator.comparing(AthleteTotalResult::getScore).reversed());
        Map<Integer, List<Integer>> rankingMap = buildRankingMap(sortedScores);

        return sortedScores.stream()
                .map(result -> result
                        .toBuilder()
                        .ranking(Rank.of(rankingMap.get(result.getScore())))
                        .build()
                ).collect(Collectors.toList());
    }

    private Map<Integer, List<Integer>> buildRankingMap(List<AthleteTotalResult> sortedScores) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // score -> ranks
        IntStream.range(0, sortedScores.size())
                .forEach(i -> {
                    Integer score = sortedScores.get(i).getScore();
                    int currentRank = i + 1;
                    if (map.containsKey(score)) {
                        map.get(score).add(currentRank);
                    } else {
                        map.put(score, new ArrayList<>(List.of(currentRank)));
                    }
                });
        return map;
    }
}
