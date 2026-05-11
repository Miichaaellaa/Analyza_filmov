package sk.spse.movie;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Label movieCountLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private TextField actorSearchField;
    @FXML
    private Button searchActorBtn;
    @FXML
    private Label actorResultLabel;
    @FXML
    private TableView<Movie> actorTableView;

    @FXML
    private TextField titleSearchField;
    @FXML
    private Button searchTitleBtn;
    @FXML
    private Label titleResultLabel;
    @FXML
    private TableView<Movie> titleTableView;

    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private Spinner<Integer> yearSpinner;
    @FXML
    private Button searchGenreYearBtn;
    @FXML
    private Label genreYearResultLabel;
    @FXML
    private TableView<Movie> genreYearTableView;

    private MovieManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manager = new MovieManager();

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1888, 2025, 2000);
        yearSpinner.setValueFactory(valueFactory);
        yearSpinner.setEditable(true);

        setupTableColumns();
        setupActions();

        statusLabel.setText("Pripravený na načítanie dát...");
        loadMovies();
    }

    private void setupTableColumns() {
        setupTableView(actorTableView);
        setupTableView(titleTableView);
        setupTableView(genreYearTableView);
    }

    private void setupTableView(TableView<Movie> tableView) {
        if (tableView.getColumns().size() >= 4) {
            @SuppressWarnings("unchecked")
            TableColumn<Movie, String> titleCol = (TableColumn<Movie, String>) tableView.getColumns().get(0);
            @SuppressWarnings("unchecked")
            TableColumn<Movie, Integer> yearCol = (TableColumn<Movie, Integer>) tableView.getColumns().get(1);
            @SuppressWarnings("unchecked")
            TableColumn<Movie, String> genresCol = (TableColumn<Movie, String>) tableView.getColumns().get(2);
            @SuppressWarnings("unchecked")
            TableColumn<Movie, String> castCol = (TableColumn<Movie, String>) tableView.getColumns().get(3);

            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
            genresCol.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGenresString()));
            castCol.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCastString()));
        }
    }

    private void setupActions() {
        actorSearchField.setOnAction(e -> searchByActor());
        titleSearchField.setOnAction(e -> searchByTitle());
        searchActorBtn.setOnAction(e -> searchByActor());
        searchTitleBtn.setOnAction(e -> searchByTitle());
        searchGenreYearBtn.setOnAction(e -> searchByGenreAndYear());
        genreComboBox.setOnAction(e -> searchByGenreAndYear());
        yearSpinner.valueProperty().addListener((obs, oldVal, newVal) -> searchByGenreAndYear());
    }

    private void loadMovies() {
        String urlPath = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

        statusLabel.setText("Načítavanie dát z internetu...");

        Thread thread = new Thread(() -> {
            manager.loadMoviesFromUrl(urlPath);

            Platform.runLater(() -> {
                movieCountLabel.setText(manager.getMovieCount() + " načítaných filmov");
                statusLabel.setText("Dáta úspešne načítané.");
                updateGenreComboBox();
            });
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void updateGenreComboBox() {
        Set<String> genres = new HashSet<>();
        for (Movie movie : manager.getAllMovies()) {
            if (movie.getGenres() != null) {
                genres.addAll(movie.getGenres());
            }
        }
        List<String> sortedGenres = new ArrayList<>(genres);
        Collections.sort(sortedGenres);
        genreComboBox.setItems(FXCollections.observableArrayList(sortedGenres));
    }

    @FXML
    private void searchByActor() {
        String actor = actorSearchField.getText().trim();
        if (actor.isEmpty()) {
            actorResultLabel.setText("Prosím, zadajte meno herca");
            return;
        }

        statusLabel.setText("Prebieha vyhľadávanie filmov s hercom: " + actor);

        List<Movie> results = manager.searchByActor(actor);
        if (results.isEmpty()) {
            actorResultLabel.setText("Nenašli sa žiadne filmy");
            actorTableView.setItems(FXCollections.observableArrayList());
        } else {
            actorResultLabel.setText("Nájdených " + results.size() + " filmov");
            actorTableView.setItems(FXCollections.observableArrayList(results));
        }
    }

    @FXML
    private void searchByTitle() {
        String title = titleSearchField.getText().trim();
        if (title.isEmpty()) {
            titleResultLabel.setText("Prosím, zadajte názov filmu");
            return;
        }

        statusLabel.setText("Prebieha vyhľadávanie filmov s názvom: " + title);

        List<Movie> results = manager.searchByTitle(title);
        if (results.isEmpty()) {
            titleResultLabel.setText("Nenašli sa žiadne filmy");
            titleTableView.setItems(FXCollections.observableArrayList());
        } else {
            titleResultLabel.setText("Nájdených " + results.size() + " filmov");
            titleTableView.setItems(FXCollections.observableArrayList(results));
        }
    }

    @FXML
    private void searchByGenreAndYear() {
        String genre = genreComboBox.getValue();
        if (genre == null || genre.trim().isEmpty()) {
            genre = genreComboBox.getEditor().getText().trim();
        }

        if (genre.isEmpty()) {
            genreYearResultLabel.setText("Prosím, zadajte žáner");
            return;
        }

        // Ošetrenie null hodnoty spinnera
        Integer yearValue = yearSpinner.getValue();
        if (yearValue == null) {
            genreYearResultLabel.setText("Prosím, zadajte rok");
            return;
        }

        int year = yearValue;

        statusLabel.setText("Prebieha vyhľadávanie " + genre + " filmov z roku " + year);

        List<Movie> results = manager.filterByGenreAndYear(genre, year);
        if (results.isEmpty()) {
            genreYearResultLabel.setText("Nenašli sa žiadne filmy");
            genreYearTableView.setItems(FXCollections.observableArrayList());
        } else {
            genreYearResultLabel.setText("Nájdených " + results.size() + " filmov");
            genreYearTableView.setItems(FXCollections.observableArrayList(results));
        }
    }
}