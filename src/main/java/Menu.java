import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public boolean menuSelectNextAction(MovieStorage storage) {
        System.out.println("1. Dodaj film\n2. Wyświetl filmy\n3. Zakończ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> {
                Movie newMovie = makeMovieFromInput();
                storage.add(newMovie);
            }
            case "2" -> storage.displayAll();
            case "3" -> {
                System.out.println("KONIEC");
                return false;
            }
            default -> System.out.println("NIE MA TAKIEJ KOMENDY");
        }

        return true;
    }

    public Movie makeMovieFromInput() {
        Movie.Builder builder = new Movie.Builder();

        System.out.println("Podaj tytuł: ");
        builder.withTitle(scanner.nextLine());

        System.out.println("Podaj rok produkcji: ");
        String yearInput = scanner.nextLine();
        builder.withProductionYear(Integer.parseInt(yearInput));

        System.out.println("Podaj gatunek: ");
        String genre = scanner.nextLine();
        builder.withGenre(Genre.valueOf(genre));

        System.out.println("Podaj ocenę: ");
        String ratingInput = scanner.nextLine();
        builder.withRating(Double.parseDouble(ratingInput));

        return builder.create();
    }
}
