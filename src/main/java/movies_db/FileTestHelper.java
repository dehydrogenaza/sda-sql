package movies_db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import movies_db.movie.Movie;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileTestHelper {
    public static void save(List<Movie> movies, File file) {
        try (FileWriter fw = new FileWriter(file)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            gson.toJson(movies, fw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Movie> read(File file) {
        LinkedList<Movie> moviesFromFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String inputJson = reader.lines().collect(Collectors.joining());

            Type listOfMoviesType = new TypeToken<LinkedList<Movie>>() {}.getType();
            Gson gson = new Gson();
            moviesFromFile = gson.fromJson(inputJson, listOfMoviesType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return moviesFromFile;
    }
}
