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
        loadTestData();
    }

    public void loadMoviesFromUrl(String urlPath) {
        try {
            URL url = new URL(urlPath);
            try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
                this.movies = new Gson().fromJson(reader, new TypeToken<List<Movie>>(){}.getType());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void loadTestData() {
        this.movies = new ArrayList<>();
        this.movies.add(new Movie("Inception", 2010,
                List.of("Leonardo DiCaprio", "Cillian Murphy"), List.of("Sci-Fi", "Akčný")));
        this.movies.add(new Movie("The Godfather", 1972,
                List.of("Marlon Brando", "Al Pacino"), List.of("Krimi", "Dráma")));
        this.movies.add(new Movie("Interstellar", 2014,
                List.of("Matthew McConaughey", "Anne Hathaway"), List.of("Sci-Fi", "Dobrodružný")));
    }

    public List<Movie> searchByActor(String actorName) {
        if (actorName == null || actorName.trim().isEmpty()) return movies;
        String token = actorName.trim().toLowerCase();
        return movies.stream()
                .filter(m -> m.getCast() != null && m.getCast().stream()
                        .anyMatch(a -> a.toLowerCase().contains(token)))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByTitle(String titleQuery) {
        if (titleQuery == null || titleQuery.trim().isEmpty()) return movies;
        String token = titleQuery.trim().toLowerCase();
        return movies.stream()
                .filter(m -> m.getTitle() != null && m.getTitle().toLowerCase().contains(token))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenreAndYear(String genre, int year) {
        boolean noGenre = (genre == null || genre.trim().isEmpty());
        String token = noGenre ? "" : genre.trim().toLowerCase();
        return movies.stream()
                .filter(m -> m.getYear() == year)
                .filter(m -> noGenre || (m.getGenres() != null &&
                        m.getGenres().stream().anyMatch(g -> g.toLowerCase().contains(token))))
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public int getMovieCount() {
        return movies.size();
    }
}