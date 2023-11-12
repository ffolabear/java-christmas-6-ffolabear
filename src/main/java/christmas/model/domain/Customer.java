package christmas.model.domain;

import christmas.model.domain.constant.Menu;
import christmas.view.InputView;
import christmas.view.Mapper;
import christmas.view.validation.DateValidator;
import christmas.view.validation.MenuValidator;
import christmas.view.validation.UserInputUtil;
import java.util.EnumMap;

public class Customer {

    private int visitDate;
    private EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
    private final InputView dateInputView = new InputView(new UserInputUtil(), new DateValidator());
    private final InputView menuInputView = new InputView(new UserInputUtil(), new MenuValidator());

    public void inputVisitDate() {
        this.visitDate = (int) dateInputView.read().data();
    }

    public void orderMenu() {
        String data = (String) menuInputView.read().data();
        this.orderedMenu = Mapper.toMenuData(data);
    }

    public int getVisitDate() {
        return visitDate;
    }

    public EnumMap<Menu, Integer> getOrderedMenu() {
        return orderedMenu;
    }
}
