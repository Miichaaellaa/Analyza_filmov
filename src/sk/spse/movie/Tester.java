package sk.spse.movie;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Testovanie s reálnymi dátami z URL\n");

        try {
            MovieManager manager = new MovieManager();
            String url = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

            System.out.println("Načítavam dáta z: " + url);
            manager.loadMoviesFromUrl(url);
            System.out.println("Načítaných filmov: " + manager.getMovieCount() + "\n");

            System.out.println("Spúšťam testy...\n");

            runTest("Sci-Fi filmy z roku 1976", () -> testSciFi1976(manager));
            runTest("Filmy s hercom Leonardo DiCaprio", () -> testActorLeonardo(manager));
            runTest("Filmy s názvom obsahujúcim 'Matrix'", () -> testTitleMatrix(manager));
            runTest("Komédie z roku 1994", () -> testComedy1994(manager));
            runTest("Horrorové filmy z roku 2020", () -> testHorror2020(manager));

            System.out.println("Všetky testy dokončené!");

        } catch (Exception e) {
            System.out.println("Chyba pri teste: " + e.getMessage());
        }
    }

    private static void runTest(String name, Runnable test) {
        System.out.println("Test: " + name);
        test.run();
        System.out.println();
    }

    private static void testSciFi1976(MovieManager manager) {
        List<Movie> results = manager.filterByGenreAndYear("Sci-Fi", 1976);
        System.out.println("Hľadám Sci-Fi filmy z roku 1976, nájdené: " + results.size());
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            Movie m = results.get(i);
            System.out.println((i+1) + ". " + m.getTitle() + " (" + m.getYear() + ")");
        }
        if (results.size() > 5) System.out.println("... ďalších " + (results.size() - 5) + " filmov");
    }

    private static void testActorLeonardo(MovieManager manager) {
        List<Movie> results = manager.searchByActor("Leonardo DiCaprio");
        System.out.println("Filmy s Leonardom DiCapriom: " + results.size());
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            Movie m = results.get(i);
            System.out.println((i+1) + ". " + m.getTitle() + " (" + m.getYear() + ")");
        }
        if (results.size() > 5) System.out.println("... ďalších " + (results.size() - 5) + " filmov");
    }

    private static void testTitleMatrix(MovieManager manager) {
        List<Movie> results = manager.searchByTitle("Matrix");
        System.out.println("Filmy obsahujúce 'Matrix' v názve: " + results.size());
        for (Movie m : results) {
            System.out.println("- " + m.getTitle() + " (" + m.getYear() + ")");
        }
    }

    private static void testComedy1994(MovieManager manager) {
        List<Movie> results = manager.filterByGenreAndYear("Comedy", 1994);
        System.out.println("Komédie z roku 1994: " + results.size());
        for (int i = 0; i < Math.min(3, results.size()); i++) {
            System.out.println((i+1) + ". " + results.get(i).getTitle());
        }
        if (results.size() > 3) System.out.println("... ďalších " + (results.size() - 3) + " komédií");
    }

    private static void testHorror2020(MovieManager manager) {
        List<Movie> results = manager.filterByGenreAndYear("Horror", 2020);
        System.out.println("Horrorové filmy z roku 2020: " + results.size());
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            System.out.println((i+1) + ". " + results.get(i).getTitle());
        }
        if (results.size() > 5) System.out.println("... ďalších " + (results.size() - 5) + " filmov");
    }
}
