package christmas.view.validation;

import christmas.model.domain.constant.Menu;
import christmas.model.domain.constant.Type;
import christmas.view.Mapper;
import christmas.view.constant.ErrorMessage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuValidator implements InputValidator<String, EnumMap<Menu, Integer>> {

    @Override
    public EnumMap<Menu, Integer> validate(String input) {
        List<String> rawMenus = Arrays.stream(input.split(",")).toList();
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        for (String rawMenu : rawMenus) {
            String[] splitMenu = validateSingleMenu(rawMenu);
            orderedMenu.put(Menu.getMenuByName(splitMenu[0]), Integer.valueOf(splitMenu[1]));
        }
        isDuplicateMenuExist(rawMenus, orderedMenu);
        EnumMap<Menu, Integer> menuData = Mapper.toMenuData(input);
        isOrderOnlyBeverage(menuData);
        return Mapper.toMenuData(input);
    }

    private String[] validateSingleMenu(String rawMenu) {
        isValidSingleMenu(rawMenu);
        String[] rawMenuDetail = rawMenu.split("-");
        isExistMenu(rawMenuDetail[0]);
        isValidOrderCount(Integer.parseInt(rawMenuDetail[1]));
        return rawMenuDetail;
    }

    private void isDuplicateMenuExist(List<String> rawMenus, EnumMap<Menu, Integer> orderedMenu) {
        if (rawMenus.size() != orderedMenu.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.getError());
        }
    }

    private void isOrderOnlyBeverage(EnumMap<Menu, Integer> menuData) {
        Map<Menu, Integer> beverageMenuData = menuData.entrySet().stream()
                .filter(entry -> entry.getKey().getType() == Type.BEVERAGE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (beverageMenuData.size() == menuData.size()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_BEVERAGE.getError());
        }
    }

    private void isValidSingleMenu(String rawMenu) {
        if (rawMenu.split("-").length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU.getError());
        }
    }

    private void isExistMenu(String input) {
        if (!Menu.isExistMenu(input)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_NOT_EXIST.getError());
        }
    }

    private void isValidOrderCount(int orderCount) {
        if (orderCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_EXCEEDED.getError());
        }
    }
}

