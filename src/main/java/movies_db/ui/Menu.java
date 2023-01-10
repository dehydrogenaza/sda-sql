package movies_db.ui;

import movies_db.actions.Action;
import movies_db.actions.AddMovieAction;
import movies_db.actions.DisplayMoviesAction;
import movies_db.actions.EndProgramAction;
import movies_db.storage.IStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final IStorage storage;
    private final Map<String, Action> menuActions = new HashMap<>();

    public Menu(IStorage storage) {
        this.storage = storage;

        Action add = new AddMovieAction(storage);
        Action display = new DisplayMoviesAction(storage);
        Action end = new EndProgramAction(storage);

        menuActions.put(add.getCommand(), add);
        menuActions.put(display.getCommand(), display);
        menuActions.put(end.getCommand(), end);
    }

    public void loop() {
        do {
            System.out.println("1. Dodaj film\n2. Wyświetl filmy\n3. Zakończ");
        } while (selectMenuAction());
        storage.close();
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
}
