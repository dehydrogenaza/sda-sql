package movies_db.storage;

import movies_db.movie.Movie;
import movies_db.ui.UI;

import java.util.List;

public interface IStorage {
    void add(Movie m);
    void displayAll();
    void close();
    default void concatenateResultsAndDisplay(List<Movie> movies) {
        StringBuilder moviesMsg = new StringBuilder();
        movies.forEach(m -> moviesMsg.append(m).append("\n"));
        UI.display(moviesMsg);
    }
}