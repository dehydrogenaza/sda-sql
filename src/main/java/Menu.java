import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final IStorage storage;

    public Menu(IStorage storage) {
        this.storage = storage;
    }

    public boolean menuSelectNextAction() {
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

        parseTitleInput(builder);
        parseYearInput(builder);
        parseGenreInput(builder);
        parseRatingInput(builder);

        return builder.create();
    }

    private void parseTitleInput(Movie.Builder builder) {
        System.out.println("Podaj tytuł: ");
        builder.withTitle(scanner.nextLine());
    }

    private void parseYearInput(Movie.Builder builder) {
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
    }

    private void parseGenreInput(Movie.Builder builder) {
        Genre genre;
        try {
            System.out.println("Podaj gatunek: ");
            genre = Genre.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Nieznany gatunek...");
            genre = Genre.UNKNOWN_GENRE;
        }
        builder.withGenre(genre);
    }

    private void parseRatingInput(Movie.Builder builder) {
        System.out.println("Podaj ocenę: ");
        String ratingInput = scanner.nextLine();
        if (!ratingInput.isBlank()) {
            builder.withRating(Double.parseDouble(ratingInput));
        }
    }
}
