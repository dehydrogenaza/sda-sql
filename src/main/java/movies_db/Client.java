package movies_db;

import movies_db.actions.*;
import movies_db.ui.Menu;

public class Client {
    public static void main(String[] args) {
        Action add = new AddMovieAction(1);
        Action display = new DisplayMoviesAction(2);
        Action chooseStorage = new ChooseStorageAction(3);
        Action end = new EndProgramAction(4);

        Menu mainMenu = new Menu(add, display, chooseStorage, end);
        mainMenu.loop();
    }
}
