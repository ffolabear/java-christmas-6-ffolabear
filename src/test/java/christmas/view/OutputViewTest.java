package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.model.domain.constant.Menu.BBQ_RIBS;
import static christmas.model.domain.constant.Menu.ICE_CREAM;
import static christmas.model.domain.constant.Menu.MUSHROOM_SOUP;
import static christmas.model.domain.constant.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.model.EventChecker;
import christmas.model.domain.Order;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageGeneratorTest extends NsTest {

    static final EventChecker eventChecker = new EventChecker();
    static final MessageGenerator messageGenerator = new MessageGenerator();
    private Order order;

    @BeforeEach
    void generateOrder() {
        EnumMap<Menu, List<Integer>> orderMenu = new EnumMap<>(Menu.class);
        orderMenu.put(BBQ_RIBS, Arrays.asList(2, BBQ_RIBS.getPrice() * 2));
        orderMenu.put(MUSHROOM_SOUP, Arrays.asList(1, MUSHROOM_SOUP.getPrice()));
        orderMenu.put(ICE_CREAM, Arrays.asList(2, ICE_CREAM.getPrice()));
        orderMenu.put(ZERO_COLA, Arrays.asList(3, ZERO_COLA.getPrice() * 3));
        LocalDate visitDate = LocalDate.of(2023, 12, 5);
        this.order = new Order(visitDate, orderMenu);
    }

    @DisplayName("안내 문구 출력 테스트")
    @Test
    void systemMessageTest() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                    "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                    "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)",
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
            );
        });
    }

    @DisplayName("주문한 매뉴 올라르게 출력되는지 테스트")
    @Test
    void orderMenuMessageTest() {
        EnumMap<Menu, List<Integer>> orderMenu = order.getOrderMenu();
        String orderedMenuMessage = messageGenerator.orderedMenu(orderMenu);
        assertThat(orderedMenuMessage).isEqualTo(
                "\n<주문 메뉴>\n"
                        + "양송이수프 1개\n"
                        + "바비큐립 2개\n"
                        + "아이스크림 2개\n"
                        + "제로콜라 3개");
    }

    @DisplayName("할인 내역을 올라르게 출력되는지 테스트")
    @Test
    void discountDetailMessageTest() {
        List<EventApplyResponse> eventApplyResponses = eventChecker.checkOrder(order);
        String discountDetailMessage = messageGenerator.discountDetail(eventApplyResponses);
        System.out.println(discountDetailMessage);
        assertThat(discountDetailMessage).isEqualTo(
                "\n<혜택 내역>\n"
                        + "평일 할인: -4,046원\n"
                        + "크리스마스 디데이 할인: -1,400원");
    }

    @DisplayName("할인 금액을 올라르게 출력되는지 테스트")
    @Test
    void totalDiscountTest() {
        List<EventApplyResponse> eventApplyResponses = eventChecker.checkOrder(order);
        String totalDiscountMessage = messageGenerator.totalDiscount(eventApplyResponses);
        assertThat(totalDiscountMessage).isEqualTo(
                "\n<총혜택 금액>\n"
                + "-5,446원");
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}