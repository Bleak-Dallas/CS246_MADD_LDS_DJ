package edu.byui.maddldsdj;

import android.util.Log;

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

    private static String CATALOG_REF_ID = "catalog";
    private static final String TAG = "CatClass";

    public Catalog() {
        this(FirebaseDatabase.getInstance().getReference(CATALOG_REF_ID));
    }

    public Catalog(DatabaseReference in_ref) {
        _songs = new ArrayList<>();
        _listeners = new ArrayList<>();
        _db = in_ref;
        _dbListener = new CatalogDBListener();
    }

    private void onCatalogReloaded() {
        for(CatalogEventListener listener : _listeners)
            listener.onCatalogReloaded();
    }

    public void addCatalogListener(CatalogEventListener listener) {
        _listeners.add(listener);
    }

    public int size() {
        return _songs.size();
    }

    public void add(Song song) throws Exception {
        if (!song.getApproved())
            throw new Exception("You can only add approved Songs to the Catalog.");

        //_db.setValue(song);
        _songs.add(song);
    }

    public void remove(Song song) {
        _songs.remove(song);
    }

    public void load() {
        _db.removeEventListener(_dbListener);
        _db.addListenerForSingleValueEvent(_dbListener);
    }

    public List<Song> getSongs() {
        return _songs;
    }

    private class CatalogDBListener implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            _songs.clear();
            Log.d(TAG, "Firebase onDataChange fired.");
            Log.d(TAG, "Retrieving data...");

            _songs.clear();
            for (DataSnapshot song : dataSnapshot.getChildren())
                _songs.add(song.getValue(Song.class));

            Log.d(TAG, "Retrieved songs... firing onCatalogReloaded");
            onCatalogReloaded();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
