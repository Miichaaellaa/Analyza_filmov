package sk.spse.movie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManager {
    private List<Movie> movies = new ArrayList<>();

    public MovieManager() {
        // Inicializácia prázdneho zoznamu
    }

    public void loadMoviesFromUrl(String urlPath) {
        try {
            URL url = new URL(urlPath);
            try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
                this.movies = new Gson().fromJson(reader, new TypeToken<List<Movie>>(){}.getType());
            }
        } catch (Exception e) {
            System.err.println("Chyba pri načítavaní dát: " + e.getMessage());
        }
    }

    // TÁTO METÓDA TI CHÝBALA (pre vyhľadávanie hercov)
    public List<Movie> searchByActor(String actorName) {
        if (actorName == null || actorName.trim().isEmpty()) return new ArrayList<>();
        String token = actorName.trim().toLowerCase();

        return movies.stream()
                .filter(m -> m.getCast() != null && m.getCast().stream()
                        .anyMatch(a -> a.toLowerCase().contains(token)))
                .collect(Collectors.toList());
    }

    // Metóda pre vyhľadávanie podľa názvu
    public List<Movie> searchByTitle(String titleQuery) {
        if (titleQuery == null || titleQuery.trim().isEmpty()) return new ArrayList<>();
        String token = titleQuery.trim().toLowerCase();

        return movies.stream()
                .filter(m -> m.getTitle() != null && m.getTitle().toLowerCase().contains(token))
                .collect(Collectors.toList());
    }

    // Metóda pre filter podľa žánru a roku
    public List<Movie> filterByGenreAndYear(String genre, int year) {
        if (genre == null || genre.trim().isEmpty()) return new ArrayList<>();
        String token = genre.trim().toLowerCase();

        return movies.stream()
                .filter(m -> m.getYear() == year)
                .filter(m -> m.getGenres() != null && m.getGenres().stream()
                        .anyMatch(g -> g.toLowerCase().contains(token)))
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public int getMovieCount() {
        return movies.size();
    }
}