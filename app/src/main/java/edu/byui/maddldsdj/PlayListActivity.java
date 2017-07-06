package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 * The PlayListActivity class
 * <p>
 * @author Dallas Bleak
 * @version 1.0
 * @since 2017-06-22
 */
public class PlayListActivity extends ListActivity implements View.OnClickListener {
    private final static String TAG = "PlayListAct";
    private Button buttonViewCatalog;
    private Context _context;
    private Catalog _catalog;
    private DatabaseReference _db = FirebaseDatabase.getInstance().getReference("DJList");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        buttonViewCatalog = (Button) findViewById(R.id.button_view_catalog);
        buttonViewCatalog.setOnClickListener(this);
        _context = this;
        _catalog = new Catalog(_db);
        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                List<Song> songs = _catalog.getSongs();
                Log.v(TAG, "songs.size(): " + songs.size());

                ArrayAdapter<Song> adapter = new PlayListAdapter(_context, songs);
                Log.v(TAG, "adapter.size(): " + adapter.getCount());
                setListAdapter(adapter);
                Log.v(TAG, "onCatalogReload called");
            }

            @Override
            public void onCatalogSongAdded(Song song) {

            }
        });
        _catalog.load();
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

    @Override
    public void onClick(View v) {
        if(v == buttonViewCatalog) {
            Log.v(TAG, "buttonViewCatalog called");
            Intent intent = new Intent(this, CatalogActivity.class);
            startActivity(intent);
        }
    }
}
