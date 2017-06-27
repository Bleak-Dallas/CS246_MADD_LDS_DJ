package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonUpVote;
    private Button buttonViewPlaylist;
    private Button buttonRequestApproval;
    ListView listViewSongs;
    DatabaseReference databaseSongs;
    List<Song> songList;
    String songSelection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonUpVote = (Button) findViewById(R.id.button_up_vote);
        buttonViewPlaylist = (Button) findViewById(R.id.button_view_playlist);
        buttonRequestApproval = (Button) findViewById(R.id.button_request_approval);

        databaseSongs = FirebaseDatabase.getInstance().getReference("catalog");

        listViewSongs = (ListView) findViewById(R.id.listViewSongs);
        songList = new ArrayList<>();

        SongList adapter = new SongList(CatalogActivity.this, songList);
        listViewSongs.setAdapter(adapter);

        // starts with Up Vote button disabled until a song is selected in the list
        buttonUpVote.setEnabled(false);

        // button selections, only has basic code for now
        buttonUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CatalogActivity.this, "Voted for song " + songSelection, Toast.LENGTH_SHORT).show();
            }
        });

        buttonViewPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CatalogActivity.this, "view Playlist Selected", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRequestApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CatalogActivity.this, "Request for song approval selected", Toast.LENGTH_SHORT).show();
            }
        });

        // maintains the catalog list view up to date when the database is changed.
        databaseSongs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                songList.clear();

                for(DataSnapshot songSnapshot : dataSnapshot.getChildren()){
                    Song song = songSnapshot.getValue(Song.class);

                    songList.add(song);
                }

                SongList adapter = new SongList(CatalogActivity.this, songList);
                listViewSongs.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // when a song is selected from the list the up vote button will be enabled and a toast will
        // display which song is selected when the up vote button is pressed.
        listViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                buttonUpVote.setEnabled(true);
                songSelection = listViewSongs.getItemAtPosition(position).toString();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    public void onClick(View v) {

    }
}
