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

    public static EnumMap<Menu, List<Integer>> toMenuData(String menuInput) {
        List<String> rawMenus = Arrays.stream(menuInput.split(",")).toList();
        EnumMap<Menu, List<Integer>> orderedMenu = new EnumMap<>(Menu.class);
        for (String rawMenu : rawMenus) {
            String[] splitMenu = rawMenu.split("-");
            Menu menu = Menu.getMenuByName(splitMenu[0]);
            int quantity = Integer.parseInt(splitMenu[1]);
            orderedMenu.put(Menu.getMenuByName(splitMenu[0]), Arrays.asList(quantity, quantity * menu.getPrice()));
        }
        return sortMenuData(orderedMenu);
    }

    private static EnumMap<Menu, List<Integer>> sortMenuData(EnumMap<Menu, List<Integer>> orderedMenu) {
        List<Map.Entry<Menu, List<Integer>>> sortedEntries = orderedMenu.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getType().ordinal()))
                .toList();
        EnumMap<Menu, List<Integer>> sortedOrderedMenu = new EnumMap<>(Menu.class);
        for (Map.Entry<Menu, List<Integer>> entry : sortedEntries) {
            sortedOrderedMenu.put(entry.getKey(), entry.getValue());
        }
        return sortedOrderedMenu;
    }
}
