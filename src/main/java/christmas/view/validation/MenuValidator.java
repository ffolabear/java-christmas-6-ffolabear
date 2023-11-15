package christmas.view.validation;

import static christmas.model.domain.constant.OrderConstant.MAX_ORDER_LIMIT;
import static christmas.model.domain.constant.OrderConstant.MIN_ORDER_LIMIT;
import static christmas.view.constant.InputConstant.MENU_DELIMITER;
import static christmas.view.constant.InputConstant.ORDER_DELIMITER;

import christmas.model.domain.constant.Menu;
import christmas.model.domain.constant.Type;
import christmas.view.mapper.Mapper;
import christmas.view.constant.ErrorMessage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class MenuValidator implements InputValidator<String, EnumMap<Menu, List<Integer>>> {

    @Override
    public EnumMap<Menu, List<Integer>> validate(String input) {
        List<String> rawMenus = Arrays.stream(input.split(ORDER_DELIMITER.getValue())).toList();
        validateInputFormat(rawMenus);
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        for (String rawMenu : rawMenus) {
            String[] splitMenu = validateSingleMenu(rawMenu);
            orderedMenu.put(Menu.getMenuByName(splitMenu[0]), Integer.valueOf(splitMenu[1]));
        }
        validateDuplicateMenu(rawMenus, orderedMenu);
        validateOrderOnlyBeverage(orderedMenu);
        return Mapper.toMenuData(input);
    }

    private void validateInputFormat(List<String> rawMenus) {
        if (rawMenus.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU.getError());
        }
    }

    private String[] validateSingleMenu(String rawMenu) {
        isValidSingleMenu(rawMenu);
        String[] rawMenuDetail = rawMenu.split(MENU_DELIMITER.getValue());
        isQuantityDigit(rawMenuDetail[1]);
        isExistMenu(rawMenuDetail[0]);
        isValidOrderCount(Integer.parseInt(rawMenuDetail[1]));
        return rawMenuDetail;
    }

    private void validateDuplicateMenu(List<String> rawMenus, EnumMap<Menu, Integer> orderedMenu) {
        if (rawMenus.size() != orderedMenu.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.getError());
        }
    }

    private void validateOrderOnlyBeverage(EnumMap<Menu, Integer> orderedMenu) {
        long beverageCount = orderedMenu.keySet().stream()
                .filter(menu -> menu.getType() == Type.BEVERAGE)
                .count();
        if (beverageCount == orderedMenu.size()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_BEVERAGE.getError());
        }
    }

    private void isValidSingleMenu(String rawMenu) {
        if (rawMenu.split(MENU_DELIMITER.getValue()).length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU.getError());
        }
    }

    private void isExistMenu(String input) {
        if (!Menu.isExistMenu(input)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_NOT_EXIST.getError());
        }
    }

    private void isQuantityDigit(String quantity) {
        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU.getError());
        }
    }

    private void isValidOrderCount(int orderCount) {
        if (orderCount < MIN_ORDER_LIMIT.getValue() || MAX_ORDER_LIMIT.getValue() < orderCount) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_EXCEEDED.getError());
        }
    }
}

