import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieStorage storage = new MovieStorage();

        mainLoop:while (true) {
            System.out.println("1. Dodaj film\n2. Wyświetl filmy\n3. Zakończ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Movie newMovie = makeMovieFromInput(scanner);
                    storage.add(newMovie);
                }
                case "2" -> storage.displayAll();
                case "3" -> {
                    System.out.println("KONIEC");
                    break mainLoop;
                }
                default -> System.out.println("NIE MA TAKIEJ KOMENDY");
            }

        }
    }

    public static Movie makeMovieFromInput(Scanner scanner) {
        System.out.println("Podaj tytuł: ");
        String title = scanner.nextLine();

        System.out.println("Podaj rok produkcji: ");
        String yearInput = scanner.nextLine();
        int productionYear = Integer.parseInt(yearInput);

        System.out.println("Podaj gatunek: ");
        String genre = scanner.nextLine();

        System.out.println("Podaj ocenę: ");
        String ratingInput = scanner.nextLine();
        double rating = Double.parseDouble(ratingInput);

        return new Movie.Builder()
                .withTitle(title)
                .withProductionYear(productionYear)
                .withGenre(Genre.valueOf(genre))
                .withRating(rating)
                .create();
    }
}
