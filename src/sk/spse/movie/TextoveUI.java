package sk.spse.movie;
import java.util.List;
import java.util.Scanner;

public class TextoveUI {
    private MovieManager manager;

    public TextoveUI() {
        this.manager = new MovieManager();
    }

    public void start() {
        String urlPath = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

        try {
            manager.loadMoviesFromUrl(urlPath);
            System.out.println("Dáta úspešne načítané!");
        } catch (Exception e) {
            System.out.println("Chyba pri načítaní dát: " + e.getMessage());
            return;
        }

        boolean running = true;

        while (running) {
            showHeader();
            showMainMenu();

            int choice = InputValidator.getMenuChoice("-> Vyberte možnosť: ", 4);

            switch (choice) {
                case 1:
                    handleActorSearch();
                    break;
                case 2:
                    handleTitleSearch();
                    break;
                case 3:
                    handleGenreYearSearch();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Neplatná voľba, prosím zvoľte čísla od 1 po 4.");
            }
        }
        System.out.println("\nĎakujeme za použitie programu!");
    }

    private void showHeader() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║           FILMOVÁ DATABÁZA          ║");
        System.out.printf ("║       %d načítaných filmov       ║\n", manager.getMovieCount());
        System.out.println("╚═════════════════════════════════════╝");
    }

    private void showMainMenu() {
        System.out.println("\nHLAVNÉ MENU:");
        System.out.println("1. Hľadať podľa herca");
        System.out.println("2. Hľadať podľa názvu filmu");
        System.out.println("3. Hľadať podľa žánru a roku");
        System.out.println("4. Ukončiť program");
        System.out.println("══════════════════════════════════════════");
    }

    private void handleActorSearch() {
        System.out.println("\nVYHĽADÁVANIE PODĽA HERCA");
        String actor = InputValidator.getString("Zadajte meno herca: ", 100);

        List<Movie> results = manager.searchByActor(actor);

        if (results.isEmpty()) {
            System.out.println("Ľutujem, nenašli sa žiadne filmy s hercom: " + actor);
        } else {
            System.out.printf("Celkový počet: %d filmov\n", results.size());
            displayPaginated(results, 10);
        }
    }

    private void handleTitleSearch() {
        System.out.println("\nVYHĽADÁVANIE PODĽA NÁZOVU FILMU");
        String title = InputValidator.getString("Zadajte názov filmu: ", 200);

        List<Movie> results = manager.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("Ľutujem, nenašli sa žiadne filmy s názvom: \"" + title + "\"");
        } else {
            System.out.printf("Celkový počet: %d filmov\n", results.size());
            displayPaginated(results, 10);
        }
    }

    private void handleGenreYearSearch() {
        System.out.println("\nVYHĽADÁVANIE PODĽA ŽÁNRU A ROKU");

        String genre = InputValidator.getString("Zadajte žáner: ", 50);
        int year = InputValidator.getYear("Zadajte rok: ");

        List<Movie> results = manager.filterByGenreAndYear(genre, year);

        if (results.isEmpty()) {
            System.out.printf("Ľutujem, nenašli sa žiadne %s filmy z roku %d\n", genre, year);
        } else {
            System.out.printf("Celkový počet: %d filmov\n", results.size());
            displayPaginated(results, 10);
        }
    }

    private void displayPaginated(List<Movie> movies, int pageSize) {
        System.out.println("══════════════════════════════════════════");

        for (int i = 0; i < movies.size(); i++) {
            System.out.printf("%3d. %s\n", i + 1, movies.get(i));

            if ((i + 1) % pageSize == 0 && i != movies.size() - 1) {
                int currentPage = (i / pageSize) + 1;
                int totalPages = (int) Math.ceil(movies.size() / (double) pageSize);

                System.out.printf("\n─── Strana %d/%d ───\n", currentPage, totalPages);
                System.out.print("Stlačte Enter pre ďalšiu stranu alebo vypíšte 'menu' a stlačte Enter pre návrat do menu: ");
                String input = new Scanner(System.in).nextLine().trim();

                if (input.equalsIgnoreCase("menu")) {
                    System.out.println("\nNávrat do hlavného menu...");
                    return;
                }
                System.out.println("══════════════════════════════════════════");
            }
        }

        System.out.println("\n══════════════════════════════════════════");
    }
}