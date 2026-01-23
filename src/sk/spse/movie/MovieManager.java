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
        if (actorName == null || actorName.trim().isEmpty()) return new ArrayList<>();
        String searchToken = actorName.trim().toLowerCase();

        return movies.stream()
                .filter(m -> m.getCast() != null && m.getCast().stream()
                        .anyMatch(a -> a.toLowerCase().contains(searchToken)))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByTitle(String titleQuery) {
        if (titleQuery == null || titleQuery.trim().isEmpty()) return new ArrayList<>();
        String searchToken = titleQuery.trim().toLowerCase();

        return movies.stream()
                .filter(m -> m.getTitle() != null &&
                        m.getTitle().toLowerCase().contains(searchToken))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenreAndYear(String genre, int year) {
        if (genre == null || genre.trim().isEmpty()) return new ArrayList<>();
        String searchToken = genre.trim();

        return movies.stream()
                .filter(m -> m.getYear() == year && m.getGenres() != null &&
                        m.getGenres().stream().anyMatch(g -> g.equalsIgnoreCase(searchToken)))
                .collect(Collectors.toList());
    }

    public int getMovieCount() {
        return movies.size();
    }
}