package edu.byui.maddldsdj;

/**
 * An individual Song
 * @author Damon Simpkinson
 * @since 10 July 2017
 * @version 1.0
 * This class holds our Song items.
 * <p>
 * All Song items reside in one of three lists.
 * <p>
 * The Catalog is already reviewed and approved for use.  These Songs can be added to the playlist
 * and voted on.
 * <p>
 * The PendingRequests list is a list of Songs that users have submitted for approval that are
 * waiting to be reviewed.
 * <p>
 * The RejectedSubmissions list is a list of Songs that have been submitted for approval.  These
 * Songs have already been reviewed and have been rejected for inclusion in the Catalog.
 */

public class Song {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int voteCount;
    private Boolean reviewed;
    private Boolean approved;
    private String key;

    // default constructor
    public Song() {
        title = "none";
        artist = "none";
        album = "none";
        genre = "none";
        reviewed = false;
        approved = false;
        voteCount = 0;
    }


/**
 * Creates a new Song item with the provided information, will set reviewed and approved to false
 * and voteCount to 0
 * @param title     the string title of the song
 * @param artist    the string Individual or Group performing the song
 * @param album     the String title of the album that the song item is located on.
 * @param genre     the String musical category the song falls under
 */
    // non-default constructor
    public Song(String title, String artist, String album, String genre){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        reviewed = false;
        approved = false;
        voteCount = 0;
    }

/**
 * Creates a new Song item with the provided information.  Will set voteCount to 0
 * @param title     the string title of the song
 * @param artist    the string Individual or Group performing the song
 * @param album     the String title of the album that the song item is located on.
 * @param genre     the String musical category the song falls under
 * @param reviewed  the boolean checking if the song been reviewed yet
 * @param approved  the boolean checking if the song has been approved
 */
    // non-default constructor
    public Song (String title, String artist, String album, String genre, Boolean reviewed, Boolean approved) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.reviewed = reviewed;
        this.approved = approved;
        voteCount = 0;
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

    public int getVoteCount() { return voteCount; }

    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }

    protected String getKey() { return key; }

    protected void setKey(String key) { this.key = key; }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Song))
            return false;

        Song s = (Song)obj;
        return (s.getTitle().equals(getTitle()) &&
                s.getArtist().equals(getArtist()));
    }
}
