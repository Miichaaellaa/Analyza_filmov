package sk.spse.movie;
import java.util.List;

public class TextoveUI {

    public void start() {
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
                    System.out.println("Neplatná voľba, prosím zvoľte čísla od 1 po 3.");
            }
        }
        System.out.println("\nĎakujem za použitie programu!");
    }

    private void showHeader() {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║           FILMOVÁ DATABÁZA          ║");
        System.out.println("║       xx načítaných filmov       ║\n");
        System.out.println("╚═════════════════════════════════════╝");
    }

    private void showMainMenu() {
        System.out.println("\nHLAVNÉ MENU:");
        System.out.println("1. Hľadať podľa herca");
        System.out.println("2. Hľadať podľa názvu filmu");
        System.out.println("3. Hľadať podľa žánru a roku");
        System.out.println("══════════════════════════════════════════");
    }

    private void handleActorSearch() {
        System.out.println("VYHĽADÁVANIE PODĽA HERCA\n");
        String actor = InputValidator.getString("Zadajte meno herca: ", 100);

        InputValidator.waitForEnter();
    }

    private void handleTitleSearch() {
        System.out.println("VYHĽADÁVANIE PODĽA NÁZOVU FILMU\n");
        String title = InputValidator.getString("Zadajte názov filmu: ", 200);

        InputValidator.waitForEnter();
    }

    private void handleGenreYearSearch() {
        System.out.println("VYHĽADÁVANIE PODĽA ŽÁNRU A ROKU\n");

        String genre = InputValidator.getString("Zadajte žáner: ", 50);
        int year = InputValidator.getYear("Zadajte rok: ");

        InputValidator.waitForEnter();
    }
}