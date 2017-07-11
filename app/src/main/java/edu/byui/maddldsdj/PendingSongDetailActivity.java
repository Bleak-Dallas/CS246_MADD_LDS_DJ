package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class PendingSongDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "PendingSongDetail";
    public static final String SONG_EXTRA = "song_extra";
    private Button buttonApprove;
    private Button buttonReject;
    private Button buttonReturnToPending;
    private Song _song;
    private Catalog _catApproved = new Catalog(FirebaseDatabase.getInstance().getReference("catalog"));
    private Catalog _catPending = new Catalog(FirebaseDatabase.getInstance().getReference("PendingRequests"));
    private Catalog _catRejected = new Catalog(FirebaseDatabase.getInstance().getReference("RejectedSubmissions"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_song_detail);
        buttonApprove = (Button) findViewById(R.id.button_approve_request);
        buttonApprove.setOnClickListener(this);
        buttonReject = (Button) findViewById(R.id.button_reject_request);
        buttonReject.setOnClickListener(this);
        buttonReturnToPending = (Button) findViewById(R.id.button_return_to_pending);
        buttonReturnToPending.setOnClickListener(this);

        Intent intent = getIntent();
        String songJson = intent.getStringExtra(SONG_EXTRA);

        // Materialize the Song
        Gson gson = new Gson();
        Song _song = gson.fromJson(songJson, Song.class);

        // Populate views from the Song
        TextView songText = (TextView)findViewById(R.id.textView_submission_title);
        TextView artistText = (TextView)findViewById(R.id.textView_submssion_artist);
        TextView albumText = (TextView)findViewById(R.id.textView_submission_album);
        TextView genreText = (TextView)findViewById(R.id.textView_submission_genre);
        songText.setText(_song.getTitle());
        artistText.setText(_song.getArtist());
        albumText.setText(_song.getAlbum());
        genreText.setText(_song.getGenre());

    }

    @Override
    public void onClick(View v) {
        if (v == buttonApprove){
            _catPending.remove(_song);
            _song.setReviewed(true);
            _song.setApproved(true);
            _catApproved.add(_song);
        }

        if (v == buttonReject){
            _catPending.remove(_song);
            _song.setReviewed(true);
            _catRejected.add(_song);
        }

        if (v == buttonReturnToPending){
            Intent dispPending = new Intent(this, PendingApproval.class);
            startActivity(dispPending);
        }


    }
}
