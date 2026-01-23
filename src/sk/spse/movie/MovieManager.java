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

    public void loadMoviesFromUrl(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            movies = new Gson().fromJson(reader, new TypeToken<List<Movie>>(){}.getType());
        }
    }

    public List<Movie> searchByActor(String actorName) {
        return movies.stream()
                .filter(m -> m.getCast() != null && m.getCast().stream()
                        .anyMatch(a -> a.toLowerCase().contains(actorName.toLowerCase().trim())))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByTitle(String titleQuery) {
        return movies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(titleQuery.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenreAndYear(String genre, int year) {
        return movies.stream()
                .filter(m -> m.getYear() == year && m.getGenres() != null &&
                        m.getGenres().stream().anyMatch(g -> g.equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
    }

    public int getMovieCount() {
        return movies.size();
    }
}