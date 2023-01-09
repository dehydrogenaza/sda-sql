public class Client {
    public static void main(String[] args) {
        Menu menu = new Menu();
        IStorage storage = new MovieStorage();

        while (menu.menuSelectNextAction(storage)) { }
    }


}
