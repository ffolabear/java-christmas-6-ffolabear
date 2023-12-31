package christmas.model.domain.constant;

import static christmas.model.domain.constant.MenuType.APPETIZER;
import static christmas.model.domain.constant.MenuType.BEVERAGE;
import static christmas.model.domain.constant.MenuType.DESSERT;
import static christmas.model.domain.constant.MenuType.MAIN;

import christmas.model.exception.MenuNotFindException;
import christmas.view.constant.ErrorMessage;
import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BBQ_RIBS("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),
    ZERO_COLA("제로콜라", 3_000, BEVERAGE),
    RED_WINE("레드와인", 60_000, BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, BEVERAGE);

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static boolean isExistMenu(String menu) {
        return Arrays.stream(Menu.values())
                .anyMatch(m -> m.getName().equals(menu));
    }

    public static Menu getMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new MenuNotFindException(ErrorMessage.MENU_NOT_FOUND.getError()));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }
}
