package movies_db;

import movies_db.storage.*;
import movies_db.ui.Menu;

public class Client {
    public static void main(String[] args) {
        IStorage storage = new InMemoryStorage();
        //IStorage storage = new DBStorage();
        Menu menu = new Menu(storage);
        menu.loop();
    }
}
