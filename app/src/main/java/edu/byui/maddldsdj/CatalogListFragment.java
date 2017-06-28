package edu.byui.maddldsdj;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Encapsulates a presentation of a Catalog for reuse in any view that wants to present the
 * Catalog
 * @author Matthew Burr
 * @since 6/24/2017.
 */

public class CatalogListFragment extends ListFragment {

    private Context _context;
    private Catalog _catalog;

    /**
     * Initializes the Catalog and its view
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _context = getActivity();
        _catalog = new Catalog();
        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                List<Song> songs = _catalog.getSongs();
                ArrayAdapter<Song> adapter = new CatalogAdapter(_context, songs);
                setListAdapter(adapter);
            }

            @Override
            public void onCatalogSongAdded(Song song) {

            }
        });
        _catalog.load();
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);

        // Serialize the song to Json
        Gson gson = new Gson();
        String jsonSong = gson.toJson(item);

        // Create intent to display song details
        Intent songDetail = new Intent(_context, SongActivity.class);

        // Add song to intent
        songDetail.putExtra(SongActivity.SONG_EXTRA, jsonSong);

        // Launch the intent
        startActivity(songDetail);
    }

}
