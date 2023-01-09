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

        while (true) {
            try {
                System.out.println("Podaj rok produkcji: ");
                String yearInput = scanner.nextLine();
                builder.withProductionYear(Integer.parseInt(yearInput));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Rok produkcji poza zakresem " + Movie.Builder.MIN_YEAR + " - " + Movie.Builder.MAX_YEAR);
            }
        }

        Genre genre;
        try {
            System.out.println("Podaj gatunek: ");
            genre = Genre.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Nieznany gatunek...");
            genre = Genre.UNKNOWN_GENRE;
        }
        builder.withGenre(genre);
        

        System.out.println("Podaj ocenę: ");
        String ratingInput = scanner.nextLine();
        builder.withRating(Double.parseDouble(ratingInput));

        return builder.create();
    }
}
