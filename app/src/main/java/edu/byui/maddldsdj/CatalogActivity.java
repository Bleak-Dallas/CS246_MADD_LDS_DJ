package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends ListActivity {

    FirebaseDatabase _firebase;
    DatabaseReference _db;
    private final static String CATALOG = "catalog";
    private final static String TAG = "CatAct";
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Catalog c = new Catalog();
        //c.load();
        _context = this;
        Log.d(TAG, "Connecting to FireBase...");

        _firebase = FirebaseDatabase.getInstance();
        _db = _firebase.getReference(CATALOG);

        _db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG, "Retrieving songs from Firebase...");
                List<Song> songs = new ArrayList<>();

                for (DataSnapshot song : dataSnapshot.getChildren())
                    songs.add(song.getValue(Song.class));

                ArrayAdapter<Song> adapter =
                        new CatalogAdapter(_context, songs);

                setListAdapter(adapter);
                Log.d(TAG, "Songs retrieved.");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
