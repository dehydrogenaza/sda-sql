import java.util.ArrayList;
import java.util.List;

public class MovieStorage {
    private final List<Movie> movies = new ArrayList<>();

    public MovieStorage add(Movie f) {
        movies.add(f);
        return this;
    }

    public void displayAll() {
        movies.forEach(System.out::println);
    }
}
