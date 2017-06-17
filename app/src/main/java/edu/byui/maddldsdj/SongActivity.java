package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Displays the details of a Song and provides a way to request it
 * @author Matthew Burr
 * @since 2017-06-16
 * @version 1.0
 */
public class SongActivity extends AppCompatActivity {

    public static final String SONG_EXTRA = "song_extra";
    private Song _song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        // Get Song from the Intent
        Intent intent = getIntent();
        String songJson = intent.getStringExtra(SONG_EXTRA);

        // Materialize the Song
        Gson gson = new Gson();
        Song song = gson.fromJson(songJson, Song.class);

        // Populate views from the Song
        TextView songText = (TextView)findViewById(R.id.songTitle);
        TextView artistText = (TextView)findViewById(R.id.songArtist);
        TextView albumText = (TextView)findViewById(R.id.songAlbum);
        songText.setText(song.getTitle());
        artistText.setText(song.getArtist());
        albumText.setText(song.getAlbum());

        // Save the Song so we can easily request it if need be
        _song = song;
    }
}
