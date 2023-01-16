package movies_db.storage;

import movies_db.ui.UI;

abstract class GenericDBStorage {
    protected static final String DATABASE_URL = "jdbc:mysql://localhost:3306/movies";
    protected static final String DATABASE_LOGIN = "root";

    protected String getDBPassword() {
        return UI.ask("Podaj hasło: ");
    }
}
