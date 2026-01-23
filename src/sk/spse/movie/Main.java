package sk.spse.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();

        List<Movie> sampleMovies = new ArrayList<>(Arrays.asList(
                new Movie("Inception", 2010, Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), Arrays.asList("Sci-Fi", "Thriller")),
                new Movie("The Revenant", 2015, Arrays.asList("Leonardo DiCaprio", "Tom Hardy"), Arrays.asList("Adventure", "Drama")),
                new Movie("Titanic", 1997, Arrays.asList("Leonardo DiCaprio", "Kate Winslet"), Arrays.asList("Romance", "Drama")),
                new Movie("Forrest Gump", 1994, Arrays.asList("Tom Hanks", "Robin Wright"), Arrays.asList("Drama", "Romance")),
                new Movie("The Terminal", 2004, Arrays.asList("Tom Hanks", "Catherine Zeta-Jones"), Arrays.asList("Comedy", "Drama")),
                new Movie("Cast Away", 2000, Arrays.asList("Tom Hanks"), Arrays.asList("Adventure", "Drama")),
                new Movie("The Dark Knight", 2008, Arrays.asList("Christian Bale", "Heath Ledger"), Arrays.asList("Action", "Crime")),
                new Movie("Interstellar", 2014, Arrays.asList("Matthew McConaughey", "Anne Hathaway"), Arrays.asList("Sci-Fi", "Adventure")),
                new Movie("Dunkirk", 2017, Arrays.asList("Fionn Whitehead", "Tom Hardy"), Arrays.asList("War", "Drama")),
                new Movie("Gladiator", 2000, Arrays.asList("Russell Crowe", "Joaquin Phoenix"), Arrays.asList("Action", "Drama")),
                new Movie("The Godfather", 1972, Arrays.asList("Marlon Brando", "Al Pacino"), Arrays.asList("Crime", "Drama")),
                new Movie("The Godfather Part II", 1974, Arrays.asList("Al Pacino", "Robert De Niro"), Arrays.asList("Crime", "Drama")),
                new Movie("Pulp Fiction", 1994, Arrays.asList("John Travolta", "Samuel L. Jackson"), Arrays.asList("Crime", "Drama")),
                new Movie("The Shawshank Redemption", 1994, Arrays.asList("Tim Robbins", "Morgan Freeman"), Arrays.asList("Drama")),
                new Movie("Fight Club", 1999, Arrays.asList("Brad Pitt", "Edward Norton"), Arrays.asList("Drama")),
                new Movie("Se7en", 1995, Arrays.asList("Brad Pitt", "Morgan Freeman"), Arrays.asList("Crime", "Thriller")),
                new Movie("The Matrix", 1999, Arrays.asList("Keanu Reeves", "Laurence Fishburne"), Arrays.asList("Sci-Fi", "Action")),
                new Movie("The Social Network", 2010, Arrays.asList("Jesse Eisenberg", "Andrew Garfield"), Arrays.asList("Drama")),
                new Movie("Avengers: Endgame", 2019, Arrays.asList("Robert Downey Jr.", "Chris Evans"), Arrays.asList("Action", "Adventure")),
                new Movie("Jurassic Park", 1993, Arrays.asList("Sam Neill", "Laura Dern"), Arrays.asList("Adventure", "Sci-Fi"))
        ));

        sampleMovies.forEach(manager::addMovie);

        System.out.println("\n--- Vyhľadávanie 'Leonardo DiCaprio' ---");
        List<Movie> leoMovies = manager.searchByActor("Leonardo DiCaprio");
        printWithPagination(leoMovies, 5);

        System.out.println("\n--- Filmy Sci-Fi z roku 2010 ---");
        List<Movie> sciFi2010 = manager.filterByGenreAndYear("Sci-Fi", 2010);
        printWithPagination(sciFi2010, 5);
    }

    private static void printWithPagination(List<Movie> movies, int pageSize) {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
            if ((i + 1) % pageSize == 0 && (i + 1) < movies.size()) {
                InputValidator.waitForEnter();
            }
        }
    }
}
