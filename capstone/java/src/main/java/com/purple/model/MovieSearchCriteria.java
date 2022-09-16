package com.purple.model;

public class MovieSearchCriteria {
    private String title;
    private String genre;
    private int minReleaseYear;
    private int maxReleaseYear;
    private int releaseYearLow;
    private int minLength;
    private int maxLength;
    private String minRating;

    public MovieSearchCriteria() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getMinReleaseYear() {
        return minReleaseYear;
    }

    public void setMinReleaseYear(int minReleaseYear) {
        this.minReleaseYear = minReleaseYear;
    }

    public int getMaxReleaseYear() {
        return maxReleaseYear;
    }

    public void setMaxReleaseYear(int maxReleaseYear) {
        this.maxReleaseYear = maxReleaseYear;
    }

    public int getReleaseYearLow() {
        return releaseYearLow;
    }

    public void setReleaseYearLow(int releaseYearLow) {
        this.releaseYearLow = releaseYearLow;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getMinRating() {
        return minRating;
    }

    public void setMinRating(String minRating) {
        this.minRating = minRating;
    }
}
