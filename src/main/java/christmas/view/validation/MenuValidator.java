package christmas.view.validation;

import christmas.model.domain.constant.Menu;
import christmas.view.constant.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuValidator implements InputValidator<String> {

    @Override
    public void validate(String input) {
        convertInput(input);
    }

    private void convertInput(String input) {
        Map<String, Integer> menuMap = new HashMap<>();
        List<String> rawMenus = Arrays.stream(input.split(",")).map(String::trim).toList();
        for (String rawMenu : rawMenus) {
            String[] rawMenuDetail = validateSingleMenu(rawMenu);
            menuMap.put(rawMenuDetail[0], Integer.valueOf(rawMenuDetail[1]));
        }
        isDuplicateMenuExist(rawMenus, menuMap);
        System.out.println(menuMap);
    }

    private String[] validateSingleMenu(String rawMenu) {
        isValidSingleMenu(rawMenu);
        String[] rawMenuDetail = rawMenu.split("-");
        isExistMenu(rawMenuDetail[0]);
        isValidOrderCount(Integer.parseInt(rawMenuDetail[1]));
        return rawMenuDetail;
    }

    private void isDuplicateMenuExist(List<String> rawMenus, Map<String, Integer> menuMap) {
        if (rawMenus.size() != menuMap.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.getError());
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
