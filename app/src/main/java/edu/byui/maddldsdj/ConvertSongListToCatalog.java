package edu.byui.maddldsdj;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.byui.maddldsdj.R;

/**
 * For one time or occasional use. Takes a list of songs stored in Firebase as a flat node of
 * strings in the form "artist - title" and moves them into the catalog node as Song objects.
 * @author Matthew Burr
 * @since 2017-06-24
 */
public class ConvertSongListToCatalog extends AppCompatActivity {

    List<String> _songList;
    Catalog _catalog;
    boolean _songListRetrieved = false;
    boolean _catalogLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_song_list_to_catalog);
        final TextView addStatus = (TextView)findViewById(R.id.addStatus);
        // We get the catalog loaded right off the bat
        _catalog = new Catalog();
        _catalog.addCatalogListener(new CatalogEventListener() {
            @Override
            public void onCatalogReloaded() {
                TextView status = (TextView)findViewById(R.id.catBackgroundStatus);
                status.setText(String.format("Catalog cached with %d songs.", _catalog.size()));
                _songListRetrieved = true;
                enableUploadButton();
            }

            @Override
            public void onCatalogSongAdded(Song song) {
                addStatus.setText(String.format("Catalog now has %d songs.", _catalog.size()));
            }
        });
        _catalog.load();
    }

    private void enableUploadButton() {
        if (_songListRetrieved && _catalogLoaded)
        {
            Button uploadButton = (Button)findViewById(R.id.uploadSongs);
            uploadButton.setVisibility(View.VISIBLE);
            TextView uploadStatus = (TextView)findViewById(R.id.addStatus);
            uploadStatus.setVisibility(View.VISIBLE);
        }
    }

    public void onGetSong(View view) {
        final TextView retrieveStatus = (TextView)findViewById(R.id.retrieveStatus);
        retrieveStatus.setVisibility(View.VISIBLE);
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference songTable = firebase.getReference("Song");

        songTable.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                _songList = (ArrayList<String>) dataSnapshot.getValue();
                retrieveStatus.setText(String.format("%d songs retrieved.", _songList.size()));
                _catalogLoaded = true;
                enableUploadButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onUploadSongs(View view) {
        for (String songString : _songList) {
            String[] parts = songString.split(" - ");
            Song song = new Song(parts[0], parts[1], "Unknown", "Unknown", true, true);
            _catalog.add(song);
        }


    }
}
