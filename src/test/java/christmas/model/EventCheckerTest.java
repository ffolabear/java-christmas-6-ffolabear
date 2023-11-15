package christmas.model;

import static christmas.model.domain.constant.Menu.BBQ_RIBS;
import static christmas.model.domain.constant.Menu.CHRISTMAS_PASTA;
import static christmas.model.domain.constant.Menu.MUSHROOM_SOUP;
import static christmas.model.domain.constant.Menu.RED_WINE;
import static christmas.model.domain.constant.Menu.T_BONE_STEAK;
import static christmas.model.domain.constant.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.domain.Order;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import christmas.model.domain.event.constant.EventConstant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventCheckerTest {

    EventChecker eventChecker = new EventChecker();

    @DisplayName("평일이벤트, 크리스마스 디데이 이벤트만 적용되는지 테스트")
    @Test
    void checkWeekdayChristmas() {
        Order order = createWeekdayChristmasOrder();
        List<EventApplyResponse> eventApplyResponses = eventChecker.checkOrder(order);
        List<EventConstant> expectedConstants = Arrays.asList(EventConstant.WEEKDAY, EventConstant.CHRISTMAS);
        List<EventConstant> actualConstants = eventApplyResponses.stream().map(EventApplyResponse::getEvent).toList();
        assertThat(actualConstants).containsExactlyInAnyOrderElementsOf(expectedConstants);
    }

    @DisplayName("평일이벤트, 스페셜 이벤트, 크리스마스 디데이 이벤트, 증정 이벤트 적용되는지 테스트")
    @Test
    void checkWeekdayChristmasSpecial() {
        Order order = createWeekdayChristmasSpecialOrder();
        List<EventApplyResponse> eventApplyResponses = eventChecker.checkOrder(order);
        List<EventConstant> expectedConstants = Arrays.asList(EventConstant.WEEKDAY, EventConstant.CHRISTMAS,
                EventConstant.SPECIAL, EventConstant.GIVEAWAY);
        List<EventConstant> actualConstants = eventApplyResponses.stream().map(EventApplyResponse::getEvent).toList();
        assertThat(actualConstants).containsExactlyInAnyOrderElementsOf(expectedConstants);
    }

    Order createWeekdayChristmasOrder() {
        EnumMap<Menu, List<Integer>> orderMenu = new EnumMap<>(Menu.class);
        orderMenu.put(BBQ_RIBS, Arrays.asList(2, BBQ_RIBS.getPrice() * 2));
        orderMenu.put(MUSHROOM_SOUP, Arrays.asList(1, MUSHROOM_SOUP.getPrice()));
        orderMenu.put(ZERO_COLA, Arrays.asList(3, ZERO_COLA.getPrice() * 3));
        LocalDate visitDate = LocalDate.of(2023, 12, 5);
        return new Order(visitDate, orderMenu);
    }

    Order createWeekdayChristmasSpecialOrder() {
        EnumMap<Menu, List<Integer>> orderMenu = new EnumMap<>(Menu.class);
        orderMenu.put(T_BONE_STEAK, Arrays.asList(2, T_BONE_STEAK.getPrice() * 2));
        orderMenu.put(CHRISTMAS_PASTA, Arrays.asList(1, CHRISTMAS_PASTA.getPrice()));
        orderMenu.put(RED_WINE, Arrays.asList(2, RED_WINE.getPrice()));
        orderMenu.put(ZERO_COLA, Arrays.asList(3, ZERO_COLA.getPrice() * 3));
        LocalDate visitDate = LocalDate.of(2023, 12, 17);
        return new Order(visitDate, orderMenu);
    }
}