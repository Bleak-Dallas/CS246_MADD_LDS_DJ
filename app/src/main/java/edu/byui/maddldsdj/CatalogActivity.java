package edu.byui.maddldsdj;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends ListActivity implements View.OnClickListener {
    private Button buttonViewPlaylist;
    private Button buttonRequestApproval;
    private Button buttonUpVote;
    private ArrayList<String> songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonViewPlaylist = (Button) findViewById(R.id.button_view_playlist);
        buttonRequestApproval = (Button) findViewById(R.id.button_request_approval);
        buttonUpVote = (Button) findViewById(R.id.button_up_vote);
        buttonViewPlaylist.setOnClickListener(this);
        buttonRequestApproval.setOnClickListener(this);
        buttonUpVote.setOnClickListener(this);

        // read from database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Song");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                songs = (ArrayList<String>) dataSnapshot.getValue();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                System.out.println("Failed to read value." + databaseError.toException());

            }
        });
        ArrayAdapter<String> adapter = new CatalogAdapter(this, songs);



 //       Catalog c = new Catalog();
 //       c.load();
 //       ArrayAdapter<Song> adapter = new CatalogAdapter(this, c.getSongs());
 //       setListAdapter(adapter);
    }
/*
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
    }*/

    @Override
    public void onClick(View v) {
        if(v == buttonViewPlaylist){
            Toast.makeText(CatalogActivity.this, "View PlayList selected", Toast.LENGTH_SHORT).show();
        }
        if(v == buttonRequestApproval){
            Toast.makeText(CatalogActivity.this, "Request song approval selected", Toast.LENGTH_SHORT).show();
        }
        if(v == buttonUpVote){
            Toast.makeText(CatalogActivity.this, "Voted for song", Toast.LENGTH_SHORT).show();
        }
    }
}
