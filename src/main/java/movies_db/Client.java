package movies_db;

import movies_db.actions.*;
import movies_db.storage.StorageManager;
import movies_db.ui.*;

public class Client {
    public static void main(String[] args) {
        Action add = new AddMovieAction(1);
        Action display = new DisplayMoviesAction(2);
        Action chooseStorage = new ChooseStorageAction(3);
        Action end = new EndProgramAction(4);

        if (args.length > 0) {
            UI.setUI(parseUICommandlineArg(args[0]));
        }

        Menu mainMenu = new Menu(add, display, chooseStorage, end);
        mainMenu.loop();
        StorageManager.close();
    }

    private static IUserInterface parseUICommandlineArg(String arg) {
        return switch (arg.toUpperCase()) {
            case "CLI" -> new ConsoleUI();
            case "GUI" -> new SimpleGUI();
            default -> throw new RuntimeException("Niewłaściwy parametr UI w linii poleceń");
        };
    }
}
