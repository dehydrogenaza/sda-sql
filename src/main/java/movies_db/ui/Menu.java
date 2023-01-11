package movies_db.ui;

import movies_db.actions.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Action> menuActions;

    public Menu(Action... actions) {
        menuActions = Arrays.stream(actions).collect(Collectors.toMap(Action::getCommand, action -> action));
    }

    public void loop() {
        do {
            System.out.println(getOptionsList());
        } while (selectMenuAction());
    }

    private boolean selectMenuAction() {
        String input = scanner.nextLine();

        if (menuActions.containsKey(input)) {
            Action action = menuActions.get(input);
            return action.performThenContinue();
        }

        System.out.println("NIE MA TAKIEJ KOMENDY");
        return true;
    }

    private String getOptionsList() {
        StringBuilder sb = new StringBuilder();
        menuActions.forEach((k, v) -> sb.append(k)
                .append(". ")
                .append(v.getDescription())
                .append("\n"));
        return sb.toString();
    }
}
