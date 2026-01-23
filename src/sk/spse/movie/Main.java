package sk.spse.movie;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();

        try {

            String url = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";
            manager.loadMoviesFromUrl(url);
            System.out.println("Dáta úspešne načítané. Počet filmov: " + manager.getMovieCount());


            TextoveUI ui = new TextoveUI();
            ui.start(manager);

        } catch (Exception e) {
            System.err.println("Chyba pri načítaní dát: " + e.getMessage());
        }
    }
}
