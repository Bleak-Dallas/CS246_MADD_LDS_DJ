package edu.byui.maddldsdj;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of approved Songs
 * @author Matthew Burr
 * @since 2017-06-10
 * @version 1.0
 */

public class Catalog {

    private List<Song> _songs;

    public Catalog() {
        _songs = new ArrayList<>();
    }

    public int size() {
        return _songs.size();
    }

    public void add(Song song) throws Exception {
        if (!song.getApproved())
            throw new Exception("You can only add approved Songs to the Catalog.");

        _songs.add(song);
    }

    public void remove(Song song) {
        _songs.remove(song);
    }

    public void load() {
        _songs.clear();

        // TODO: Replace this code with a call to the Firebase
        try {
            add(new Song("Bohemian Rhapsody", "Queen", "A Night at the Opera", "Rock", true, true));
            add(new Song("Come Together", "Beatles", "Abbey Road", "Rock", true, true));
            add(new Song("Wouldn't It Be Nice", "The Beach Boys", "Pet Sounds", "Rock", true, true));
            add(new Song("Evenflow", "Pearl Jam", "Ten", "Rock", true, true));
            add(new Song("Come As You Are", "Nirvana", "Nevermind", "Rock", true, true));
        } catch (Exception ex) {

        }
    }

    public List<Song> getSongs() {
        return _songs;
    }
}
