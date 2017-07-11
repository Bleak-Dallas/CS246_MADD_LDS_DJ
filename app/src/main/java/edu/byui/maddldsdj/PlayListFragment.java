package edu.byui.maddldsdj;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.List;

/**
 * Encapsulates a presentation of a PlayList for reuse in any view that wants to present the
 * PlayList
 * @author Matthew Burr
 * @since 6/24/2017.
 */

public class PlayListFragment extends ListFragment{
    private final static String TAG = "PlayListAct";
    private Button buttonViewCatalog;
    private Context _context;
    private Catalog _catalog;
    List<Song> _songs;
    private PlayListAdapter _adapter;
    private DatabaseReference _db = FirebaseDatabase.getInstance().getReference("DJList");

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated starts.");
        super.onActivityCreated(savedInstanceState);
        _context = getActivity();
        _catalog = new Catalog(_db);
        _songs = _catalog.getSongs();
        _adapter = new PlayListAdapter(_context, _songs);

        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                Log.d(TAG, "onCatalogReloaded starts.");
                setListAdapter(_adapter);
                Log.d(TAG, "onCatalogReloaded ends.");
            }

            @Override
            public void onCatalogSongAdded(Song song) {

            }
        });
        Log.d(TAG, "onActivityCreated ends.");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart starts.");
        super.onStart();
        _catalog.load();
        Log.d(TAG, "onStart ends.");
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);

        // Serialize the song to Json
        Gson gson = new Gson();
        String jsonSong = gson.toJson(item);

        // Create intent to display song details
        Intent songDetail = new Intent(_context, SongPlayListActivity.class);

        // Add song to intent
        songDetail.putExtra(SongActivity.SONG_EXTRA, jsonSong);

        // Launch the intent
        startActivity(songDetail);
    }
}
