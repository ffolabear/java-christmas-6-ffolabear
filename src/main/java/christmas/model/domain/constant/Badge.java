package christmas.model.domain.constant;

import java.util.stream.Stream;

public enum Badge {

    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int qualification;

    Badge(String name, int qualification) {
        this.name = name;
        this.qualification = qualification;
    }

    public static Badge findHighestBadge(int totalDiscount) {
        return Stream.of(values())
                .filter(badge -> badge.qualification < totalDiscount)
                .reduce((none, highest) -> highest)
                .orElse(Badge.NONE);
    }

    public String getName() {
        return name;
    }
}
