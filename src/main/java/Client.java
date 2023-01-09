public class Client {
    public static void main(String[] args) {
        Menu menu = new Menu();
        MovieStorage storage = new MovieStorage();

        while (menu.menuSelectNextAction(storage)) { }
    }


}
