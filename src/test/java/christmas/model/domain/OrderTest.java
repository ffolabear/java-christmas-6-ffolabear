package christmas.model.domain;

import static christmas.model.domain.constant.Menu.CHRISTMAS_PASTA;
import static christmas.model.domain.constant.Menu.RED_WINE;
import static christmas.model.domain.constant.Menu.T_BONE_STEAK;
import static christmas.model.domain.constant.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.domain.constant.Menu;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {

    @DisplayName("입력한 날짜와 주문의 날짜가 일치하는지 테스트")
    @ParameterizedTest
    @MethodSource("generateLocalDate")
    void createOrderTest(Order order, LocalDate compareDate) {
        assertThat(order.getVisitDate()).isEqualTo(compareDate);
    }

    static Stream<Arguments> generateLocalDate() {
        return Stream.of(
                Arguments.arguments(new Order(LocalDate.of(2023, 12, 1), generateEnumMap()),
                        LocalDate.of(2023, 12, 1)),
                Arguments.arguments(new Order(LocalDate.of(2023, 12, 15), generateEnumMap()),
                        LocalDate.of(2023, 12, 15)),
                Arguments.arguments(new Order(LocalDate.of(2023, 12, 31), generateEnumMap()),
                        LocalDate.of(2023, 12, 31))
        );
    }

    static EnumMap<Menu, List<Integer>> generateEnumMap() {
        EnumMap<Menu, List<Integer>> orderMenu = new EnumMap<>(Menu.class);
        orderMenu.put(T_BONE_STEAK, Arrays.asList(2, T_BONE_STEAK.getPrice() * 2));
        orderMenu.put(CHRISTMAS_PASTA, Arrays.asList(1, CHRISTMAS_PASTA.getPrice()));
        orderMenu.put(RED_WINE, Arrays.asList(2, RED_WINE.getPrice()));
        orderMenu.put(ZERO_COLA, Arrays.asList(3, ZERO_COLA.getPrice() * 3));
        return orderMenu;
    }

}