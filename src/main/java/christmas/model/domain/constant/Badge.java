package christmas.model.domain.constant;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int qualification;

    Badge(String name, int qualification) {
        this.name = name;
        this.qualification = qualification;
    }
}
