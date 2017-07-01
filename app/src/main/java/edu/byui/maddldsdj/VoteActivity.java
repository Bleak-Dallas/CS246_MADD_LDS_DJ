package edu.byui.maddldsdj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by thesh on 7/1/2017.
 */

public class VoteActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "VoteActivity";
    private Button buttonUpVote;
    private Catalog _catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play_list);
        buttonUpVote = (Button) findViewById(R.id.btnvoteup);
        buttonUpVote.setOnClickListener(this);
        Log.d(TAG, "onCreate of VoteActivity executed");
    }

    @Override
    public void onClick(View view){
        Log.d(TAG, "onClick activity entered");
        if (view == buttonUpVote){
            Toast.makeText(VoteActivity.this, "Vote Incremented", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onClick activity executed");
        }
    }

}
