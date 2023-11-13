package christmas.view;

import christmas.model.domain.constant.Menu;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    public static LocalDate toLocalDate(int dateInput) {
        return LocalDate.of(YEAR, MONTH, dateInput);
    }

    public static EnumMap<Menu, Integer> toMenuData(String menuInput) {
        List<String> rawMenus = Arrays.stream(menuInput.split(",")).toList();
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        for (String rawMenu : rawMenus) {
            String[] splitMenu = rawMenu.split("-");
            orderedMenu.put(Menu.getMenuByName(splitMenu[0]), Integer.valueOf(splitMenu[0]));
        }
        return sortMenuData(orderedMenu);
    }

    private static EnumMap<Menu, Integer> sortMenuData(EnumMap<Menu, Integer> orderedMenu) {
        List<Map.Entry<Menu, Integer>> sortedEntries = orderedMenu.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getType().ordinal()))
                .toList();
        EnumMap<Menu, Integer> sortedOrderedMenu = new EnumMap<>(Menu.class);
        for (Map.Entry<Menu, Integer> entry : sortedEntries) {
            sortedOrderedMenu.put(entry.getKey(), entry.getValue());
        }
        return sortedOrderedMenu;
    }
}
