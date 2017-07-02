package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

/**
 * Displays the details of a Song and provides a way to request it
 * @author Matthew Burr
 * @author Damon Simpkinson
 * @since 2017-06-16
 * @version 1.0
 */
public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SONG_EXTRA = "song_extra";
    private Song _song;
    private Button buttonAddToPlaylist;
    private Button buttonReturnToCatalog;
    private DatabaseReference _db = FirebaseDatabase.getInstance().getReference("DJList");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        buttonAddToPlaylist = (Button) findViewById(R.id.button_add_to_playlist);
        buttonAddToPlaylist.setOnClickListener(this);
        buttonReturnToCatalog = (Button) findViewById(R.id.button_Return_To_Catalog_from_song);
        buttonReturnToCatalog.setOnClickListener(this);

        // Get Song from the Intent
        Intent intent = getIntent();
        String songJson = intent.getStringExtra(SONG_EXTRA);

        // Materialize the Song
        Gson gson = new Gson();
        Song song = gson.fromJson(songJson, Song.class);

        // Populate views from the Song
        TextView songText = (TextView)findViewById(R.id.playSongTitle);
        TextView artistText = (TextView)findViewById(R.id.playSongArtist);
        TextView albumText = (TextView)findViewById(R.id.playSongAlbum);
        songText.setText(song.getTitle());
        artistText.setText(song.getArtist());
        albumText.setText(song.getAlbum());
        // Save the Song so we can easily request it if need be
        _song = song;
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAddToPlaylist) {
            Toast.makeText(this, _song.getTitle() + " add to playlist", Toast.LENGTH_SHORT).show();
            _db.push().setValue(_song);

            // Create intent to display song details
            Intent dispPlayList = new Intent(this, PlayListActivity.class);

            // Launch the intent
            startActivity(dispPlayList);
        }

        if (v == buttonReturnToCatalog){
            // Intent to return to the catalog
            Intent dispCatalog = new Intent(this, CatalogActivity.class);
            
            // launch intent
            startActivity(dispCatalog);
        }
    }
}
