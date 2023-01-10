package movies_db;

public class Client {
    public static void main(String[] args) {
        //IStorage storage = new InMemoryStorage();
        IStorage storage = new DBStorage();
        Menu menu = new Menu(storage);
        menu.loop();
    }
}
