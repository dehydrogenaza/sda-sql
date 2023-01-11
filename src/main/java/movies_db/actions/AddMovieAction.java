package movies_db.actions;

import movies_db.movie.*;
import movies_db.storage.StorageManager;
import movies_db.ui.UI;

public class AddMovieAction extends Action {
    public AddMovieAction(int commandNumber) { super(commandNumber); }
    @Override
    public boolean performThenContinue() {
        Movie newMovie = makeMovieFromInput();
        StorageManager.getStorage().add(newMovie);
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
        builder.withTitle(UI.ask("Podaj tytuł: "));
    }

    private void parseYearInput(Movie.Builder builder) {
        while (true) {
            try {
                String yearInput = UI.ask("Podaj rok produkcji: ");
                builder.withProductionYear(Integer.parseInt(yearInput));
                break;
            } catch (IllegalArgumentException e) {
                UI.display("Rok produkcji poza zakresem " + Movie.Builder.MIN_YEAR + " - " + Movie.Builder.MAX_YEAR);
            }
        }
    }

    private void parseGenreInput(Movie.Builder builder) {
        Genre genre;
        try {
            genre = Genre.valueOf(UI.ask("Podaj gatunek: "));
        } catch (IllegalArgumentException e) {
            UI.display("Nieznany gatunek...");
            genre = Genre.UNKNOWN_GENRE;
        }
        builder.withGenre(genre);
    }

    private void parseRatingInput(Movie.Builder builder) {
        String ratingInput = UI.ask("Podaj ocenę: ");
        if (!ratingInput.isBlank()) {
            builder.withRating(Double.parseDouble(ratingInput));
        }
    }

    @Override
    public String getDescription() {
        return "Dodaj film";
    }
}
