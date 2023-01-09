import java.util.ArrayList;
import java.util.List;

public class FilmStorage {
    private final List<Film> films = new ArrayList<>();

    public FilmStorage add(Film f) {
        films.add(f);
        return this;
    }

    public void displayAll() {
        films.forEach(System.out::println);
    }
}
