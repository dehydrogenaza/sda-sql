public class Client {
    public static void main(String[] args) {
        IStorage storage = new MovieStorage();
        Menu menu = new Menu(storage);

        while (menu.menuSelectNextAction()) { }
    }


}
