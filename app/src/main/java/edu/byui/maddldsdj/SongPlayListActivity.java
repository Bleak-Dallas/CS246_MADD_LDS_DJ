package edu.byui.maddldsdj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
 * Displays the details of a Song with the vote count and
 * provdes a way for the user to vote for songs
 * <p>
 * @author Dallas Bleak
 * @version 1.0
 * @since 2017-06-22
 */
public class SongPlayListActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "SongPlayListActivity";
    public static final String SONG_EXTRA = "song_extra";
    private static final String USERPREF = "UserPref";

    private Song _song;
    private Catalog _catalog;
    private DatabaseReference _db = FirebaseDatabase.getInstance().getReference("DJList");
    private Button btnVoteUp, btnVoteDown, btnRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play_list);
        _catalog = new Catalog(_db);

        // Buttons
        btnVoteUp = (Button) findViewById(R.id.btnvoteup);
        btnVoteDown = (Button) findViewById(R.id.btnvotedown);
        btnRemove = (Button) findViewById(R.id.btnremoveplaylist);
        setButtonVisibility();

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
        TextView voteText = (TextView)findViewById(R.id.votes);
        songText.setText(song.getTitle());
        artistText.setText(song.getArtist());
        albumText.setText(song.getAlbum());

        // set the vote count in the view
        try
        {
            voteText.setText(String.valueOf(song.getVoteCount()));
            int countVal = song.getVoteCount();
            Log.d(TAG, "countVal is: " + countVal);
        }catch(Exception ex)
        {
            Log.d(TAG, "We have an error on getVoteCount()");
            Log.d(TAG, ex.toString());
        }

        //create onClick listener for button
        btnVoteUp.setOnClickListener(this);
        btnVoteDown.setOnClickListener(this);

        // Save the Song so we can easily request it if need be
        _song = song;
    }

   private void setButtonVisibility() {
       // get admin from Shared Preferences
       SharedPreferences userPreferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
       boolean useradmin = userPreferences.getBoolean("userAdmin", false);
       // if usr is an admin show remove song button and hide vote buttons
        if (useradmin) {
            btnRemove.setVisibility(View.VISIBLE);
            btnVoteUp.setVisibility(View.INVISIBLE);
            btnVoteDown.setVisibility(View.INVISIBLE);
            // if user is NOT admin show vote buttons and hide remove song button
        } else {
            btnRemove.setVisibility(View.INVISIBLE);
            btnVoteUp.setVisibility(View.VISIBLE);
            btnVoteDown.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view){
        Log.d(TAG, "onClick activity entered");

        TextView title = (TextView) findViewById(R.id.playSongTitle);
        TextView artist = (TextView) findViewById(R.id.playSongArtist);
        TextView album = (TextView) findViewById(R.id.playSongAlbum);
        TextView votes = (TextView) findViewById(R.id.votes);

        Song item = new Song();
        item.setAlbum(album.getText().toString());
        item.setArtist(artist.getText().toString());
        item.setTitle(title.getText().toString());

        int theVote = Integer.valueOf(votes.getText().toString());

        if(view == btnVoteUp) {
            //Increment the vote count
            theVote++;

            //set the new value to song
            item.setVoteCount(theVote);

            //push song to firebase
            _catalog.add(item);
             Toast.makeText(SongPlayListActivity.this, "Vote Incremented", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Vote Incremented");
        }

        if(view == btnVoteDown) {
            //Increment the vote count
            theVote--;

            //set the new value to song
            item.setVoteCount(theVote);

            //push song to firebase
            _catalog.add(item);
            Toast.makeText(SongPlayListActivity.this, "Vote Decremented", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Vote Decremented");
        }

        Intent reload = new Intent(this, PlayListActivity.class);

        startActivity(reload);

    }
}
