package movies_db.actions;

import movies_db.movie.Genre;
import movies_db.movie.Movie;
import movies_db.storage.IStorage;

import java.util.Scanner;

public class AddMovieAction extends Action {
    private final Scanner scanner = new Scanner(System.in);
    public AddMovieAction(IStorage storage) {
        super(storage);
    }
    @Override
    public boolean performThenContinue() {
        Movie newMovie = makeMovieFromInput();
        storage.add(newMovie);
        return true;
    }

    private Movie makeMovieFromInput() {
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

    @Override
    public String getCommand() {
        return "1";
    }
}
