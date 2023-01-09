import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainLoop:while (true) {
            System.out.println("1. Dodaj film\n2. Wyświetl filmy\n3. Zakończ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> System.out.println("Opcja 1");
                case "2" -> System.out.println("Opcja 2");
                case "3" -> {
                    System.out.println("KONIEC");
                    break mainLoop;
                }
                default -> System.out.println("NIE MA TAKIEJ KOMENDY");
            }
        }
    }
}
