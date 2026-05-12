package sk.spse.movie;

import java.util.List;

public class Movie {
    private String title;
    private int year;
    private List<String> cast;
    private List<String> genres;
    private String imageUrl;
    private String movieUrl;

    public Movie() {}

    public Movie(String title, int year, List<String> cast, List<String> genres, String imageUrl, String movieUrl) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.imageUrl = imageUrl;
        this.movieUrl = movieUrl;
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }
    public List<String> getCast() { return cast; }
    public List<String> getGenres() { return genres; }
    public String getImageUrl() { return imageUrl; }
    public String getMovieUrl() { return movieUrl; }

    public String getGenresString() {
        return (genres != null) ? String.join(", ", genres) : "";
    }

    public String getCastString() {
        return (cast != null) ? String.join(", ", cast) : "";
    }
}