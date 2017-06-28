package edu.byui.maddldsdj;

/**
 * Created by thesh on 6/24/2017.
 */

public class DJList extends Song {
    // Variables to identify songs
    private String title;
    private String artist;
    private String album;
    private String genre;
    // has the song been reviewed?
    private Boolean reviewed;
    // has the song been approved for use at church dances?
    private Boolean approved;
    // added Votes
    private int voteCount;

    public DJList() {
        title = "none";
        artist = "none";
        album = "none";
        genre = "none";
        reviewed = false;
        approved = false;
        voteCount = 0;
    }

    // non-default constructor
    public DJList(String title, String artist, String album, String genre, int votes){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        reviewed = false;
        approved = false;
        this.voteCount = votes;
    }

    // non-default constructor
    public DJList (String title, String artist, String album, String genre, int votes, Boolean reviewed, Boolean approved) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.reviewed = reviewed;
        this.approved = approved;
        this.voteCount = votes;
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

    public void setVoteCount(int votes) {this.voteCount = votes; };

    public int getVoteCount() { return voteCount; };

    @Override
    public String toString() {
        return getTitle();
    }
}
