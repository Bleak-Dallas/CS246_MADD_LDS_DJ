package edu.byui.maddldsdj;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private FirebaseDatabase _server;
    private DatabaseReference _db;
    private List<CatalogEventListener> _listeners;
    private ValueEventListener _dbListener;
    private ChildEventListener _dbSongListener;

    private static String CATALOG_REF_ID = "catalog";
    private static final String TAG = "CatClass";

    /**
     * Creates a new instance of Catalog with the default FirebaseDatabase reference
     */
    public Catalog() {
        this(FirebaseDatabase.getInstance().getReference(CATALOG_REF_ID));
    }

    /**
     * Creates a new instance of Catalog with the specific FirebaseDatabase reference
     * @param in_ref Reference to the FirebaseDatabase node that stores the Catalog in Firebase
     */
    public Catalog(DatabaseReference in_ref) {
        _songs = new ArrayList<>();
        _listeners = new ArrayList<>();
        _db = in_ref;
        _dbListener = new CatalogDBListener();
        _dbSongListener = new CatalogSongListener();
    }

    private void onCatalogReloaded() {
        for(CatalogEventListener listener : _listeners)
            listener.onCatalogReloaded();
    }

    private void onCatalogSongAdded(Song song) {
        for (CatalogEventListener listener : _listeners)
            listener.onCatalogSongAdded(song);
    }

    /**
     * Adds a new listener for Catalog events
     * @param listener a {@link=CatalogEventListener} that will listen to Catalog events
     */
    public void addCatalogListener(CatalogEventListener listener) {
        _listeners.add(listener);
    }

    /**
     * Gets the number of songs in the Catalog
     * @return The number of songs in the Catalog
     */
    public int size() {
        return _songs.size();
    }

    /**
     * Adds a new Song to the Catalog (locally and in Firebase)
     * @param song The song to be added
     */
    public void add(Song song) {
        _db.push().setValue(song);
    }

    /**
     * Removes a Song from the Catalog
     * @param song The Song to be removed
     */
    public void remove(Song song) {
        _songs.remove(song);
    }

    /**
     * Dumps and reloads the Catalog's content from the Firebase
     */
    public void load() {
        Log.d(TAG, "load called.");
        _db.removeEventListener(_dbListener);
        _db.addListenerForSingleValueEvent(_dbListener);
        _db.addChildEventListener(_dbSongListener);
    }

    /**
     * Gets the Catalog's contents as a List for easy use in presentation
     * @return The list of Songs in the Catalog
     */
    public List<Song> getSongs() {
        return _songs;
    }

    private class CatalogDBListener implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            _songs.clear();
            Log.d(TAG, "Firebase onDataChange fired.");
            Log.d(TAG, "Retrieving data...");

            for (DataSnapshot song : dataSnapshot.getChildren())
                _songs.add(song.getValue(Song.class));

            Log.d(TAG, "Retrieved songs... firing onCatalogReloaded");
            onCatalogReloaded();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }

    private class CatalogSongListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "Firebase onChildADded fired.");
            Log.d(TAG, "Adding song...");

            Song addedSong = dataSnapshot.getValue(Song.class);
            _songs.add(addedSong);

            Log.d(TAG, "Added song to local cache... firing onCatalogSongAdded");
            onCatalogSongAdded(addedSong);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
