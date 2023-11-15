package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest extends NsTest {

    private static final String VALID_MENU = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

    @DisplayName("날짜 입력 테스트")
    @Nested
    class DateInputTest {

        @DisplayName("날짜가 숫자가 아닌지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"a", "-", "+", "가", "java"})
        void dataInputDigitTest(String dateInput) {
            assertSimpleTest(() -> {
                run(dateInput, "1", VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
                );

            });
        }

        @DisplayName("날짜가 올바른 범위인지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"-1", "32", "50", "100", "0"})
        void dataInputRangeTest(String dateInput) {
            assertSimpleTest(() -> {
                run(dateInput, "1", VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
                );

            });
        }

        @DisplayName("날짜 입력 성공 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"1", "15", "22", "31"})
        void dataInputSuccessTest(String dateInput) {
            assertSimpleTest(() -> {
                run(dateInput, VALID_MENU);
                assertThat(output()).doesNotContain(
                        "[ERROR]"
                );

            });
        }

    }


    @DisplayName("매뉴 입력 테스트")
    @Nested
    class MenuInputTest {

        private static final String DATE = "1";

        @DisplayName("매뉴와 매뉴 사이가 올바르게 나뉘어져 있는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-1-바비큐립-1", "티본스테이크=1.바비큐립*1", "티본스테이크:1|바비큐립:1"})
        void menuInputOrderDelimiterTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 주문입니다."
                );
            });
        }

        @DisplayName("매뉴이름과 매뉴 주문 수량이 올바르게 나뉘어져 있는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크=1,바비큐립-1", "티본스테이크=1,바비큐립*1", "티본스테이크:1,바비큐립:1"})
        void menuInputMenuDelimiterTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 주문입니다."
                );
            });
        }

        @DisplayName("중복된 매뉴가 존재하는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-1,바비큐립-1,티본스테이크-2", "티본스테이크-1,바비큐립-1,바비큐립-2"})
        void menuInputUniqueTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 주문입니다."
                );
            });
        }

        @DisplayName("음료만 주문하는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"제로콜라-1,레드와인-1,샴페인-2", "제로콜라-5,레드와인-3"})
        void menuInputOnlyBeverageTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."
                );
            });
        }

        @DisplayName("미리 지정되어 있는 매뉴를 주문하는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"후라이드치킨-1,레드와인-1,샴페인-2", "떡볶이-5,레드와인-3", "막국수-3,제로콜라-3"})
        void menuInputExistTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
                );
            });
        }

        @DisplayName("올바른 수량의 매뉴를 주문하는지 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-50,바비큐립-1", "티본스테이크-300,바비큐립-1,바비큐립-2"})
        void menuInputValidQuantityTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).contains(
                        "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."
                );
            });
        }

        @DisplayName("매뉴 입력 성공 테스트")
        @ParameterizedTest
        @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", "바비큐립-1,초코케이크-2,타파스-1"})
        void menuInputSuccessTest(String menuInput) {
            assertSimpleTest(() -> {
                run(DATE, menuInput, VALID_MENU);
                assertThat(output()).doesNotContain(
                        "[ERROR]"
                );

            });
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}