package christmas.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.domain.constant.Badge;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 8_000, 12_000, 10_000, 20_000, 25_000})
    void createBadgeTest(int price) {
        Badge badge = Badge.findHighestBadge(price);
        System.out.println(badge);
        assertThat(badge.getQualification()).isLessThanOrEqualTo(price);
    }

}
