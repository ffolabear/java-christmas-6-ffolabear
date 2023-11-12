package christmas.view;

import christmas.model.domain.constant.Menu;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Mapper {

    public static EnumMap<Menu, Integer> toMenuData(String input) {
        List<String> rawMenus = Arrays.stream(input.split(",")).toList();
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        for (String rawMenu : rawMenus) {
            String[] splitMenu = rawMenu.split("-");
            orderedMenu.put(Menu.getMenuByName(splitMenu[0]), Integer.valueOf(splitMenu[0]));
        }
        return orderedMenu;
    }

}
