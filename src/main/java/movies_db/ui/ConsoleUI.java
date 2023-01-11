package movies_db.ui;

import java.util.Scanner;

public class ConsoleUI implements IUserInterface {
    Scanner inputScanner = new Scanner(System.in);
    @Override
    public void display(String msg) {
        System.out.println(msg);
    }

    @Override
    public String ask(String msg) {
        if (msg != null) {
            System.out.println(msg);
        }
        return inputScanner.nextLine();
    }
}
