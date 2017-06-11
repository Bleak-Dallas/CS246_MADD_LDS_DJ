package edu.byui.maddldsdj;

/**
 * An individual Song
 * @author Damon Simpkinson
 * @since 10 July 2017
 * @version 1.0
 */

public class Song {
    // Variables to identify songs
    private String title;
    private String artist;
    private String album;
    private String genre;
    // has the song been reviewed?
    private Boolean reviewed;
    // has the song been approved for use at church dances?
    private Boolean approved;

    // default constructor
    public Song() {
        title = "none";
        artist = "none";
        album = "none";
        genre = "none";
        reviewed = false;
        approved = false;
    }

    // non-default constructor
    public Song(String title, String artist, String album, String genre){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        reviewed = false;
        approved = false;
    }

    // non-default constructor
    public Song (String title, String artist, String album, String genre, Boolean reviewed, Boolean approved) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.reviewed = reviewed;
        this.approved = approved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

}
