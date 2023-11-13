package christmas.view.validation;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputUtil;

public class UserInputUtil implements InputUtil<String> {

    @Override
    public String read() {
        return Console.readLine();
    }

}