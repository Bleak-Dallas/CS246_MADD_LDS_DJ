package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

public class PlayListActivity extends ListActivity {
    private final static String TAG = "PlayListAct";
    private Context _context;
    private Catalog _catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* _context = this;
        _catalog = new Catalog();
        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                List<Song> songs = _catalog.getSongs();
                ArrayAdapter<Song> adapter = new PlayListAdapter(_context, songs);
                setListAdapter(adapter);
            }
        });
        _catalog.load();*/
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);

        // Serialize the song to Json
        Gson gson = new Gson();
        String jsonSong = gson.toJson(item);

        // Create intent to display song details
        Intent songDetail = new Intent(this, SongPlayListActivity.class);

        // Add song to intent
        songDetail.putExtra(SongPlayListActivity.SONG_EXTRA, jsonSong);

        // Launch the intent
        startActivity(songDetail);
    }
}
