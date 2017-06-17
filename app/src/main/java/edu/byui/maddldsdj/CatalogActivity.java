package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class CatalogActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Catalog c = new Catalog();
        c.load();
        ArrayAdapter<Song> adapter = new CatalogAdapter(this, c.getSongs());
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Song item = (Song) getListAdapter().getItem(position);

        // Serialize the song to Json
        Gson gson = new Gson();
        String jsonSong = gson.toJson(item);

        // Create intent to display song details
        Intent songDetail = new Intent(this, SongActivity.class);

        // Add song to intent
        songDetail.putExtra(SongActivity.SONG_EXTRA, jsonSong);

        // Launch the intent
        startActivity(songDetail);
    }
}
