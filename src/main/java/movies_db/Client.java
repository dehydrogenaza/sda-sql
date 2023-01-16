package movies_db;

import movies_db.actions.*;
import movies_db.storage.StorageManager;
import movies_db.storage.StorageType;
import movies_db.ui.*;

import java.util.Arrays;

public class Client {
    private static final String CLI_MODE = "cli";
    private static final String GUI_MODE = "gui";
    private static final String MEMORY_STORAGE_MODE = "memory";
    private static final String JDBC_STORAGE_MODE = "jdbc";
    private static final String HIBERNATE_STORAGE_MODE = "hibernate";

    public static void main(String[] args) {
        parseCommandlineArgs(args);

        createMenu().loop();

        StorageManager.close();
    }

    private static Menu createMenu() {
        Action add = new AddMovieAction(1);
        Action display = new DisplayMoviesAction(2);
        Action chooseStorage = new ChooseStorageAction(3);
        Action end = new EndProgramAction(4);

        return new Menu(add, display, chooseStorage, end);
    }

    private static void parseCommandlineArgs(String[] args) {
        if (args.length == 0) return;

        if (Arrays.stream(args).anyMatch( arg -> arg.equalsIgnoreCase(CLI_MODE))) {
            UI.setUI(new ConsoleUI());
        } else if (Arrays.stream(args).anyMatch( arg -> arg.equalsIgnoreCase(GUI_MODE))) {
            UI.setUI(new SimpleGUI());
        }

        if (Arrays.stream(args).anyMatch( arg -> arg.equalsIgnoreCase(MEMORY_STORAGE_MODE))) {
            StorageManager.setInitialStorage(StorageType.IN_MEMORY);
        } else if (Arrays.stream(args).anyMatch( arg -> arg.equalsIgnoreCase(JDBC_STORAGE_MODE))) {
            StorageManager.setInitialStorage(StorageType.JDBC);
        } else if (Arrays.stream(args).anyMatch( arg -> arg.equalsIgnoreCase(HIBERNATE_STORAGE_MODE))) {
            StorageManager.setInitialStorage(StorageType.HIBERNATE);
        }
    }
}
