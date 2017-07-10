package edu.byui.maddldsdj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * User interface with the catalog allowing navigation to song details, DJ playlist and new song
 * request page.
 *
 * @author Damon Simpkinson
 * @since 6/29/2017.
 */

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CatalogActivity";
    private static final String USERPREF = "UserPref";
    private Button buttonRequestApproval;
    private Button buttonViewPlaylist;
    private Button buttonViewPending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonRequestApproval = (Button) findViewById(R.id.button_Request_Submission);
        buttonRequestApproval.setOnClickListener(this);
        buttonViewPlaylist = (Button) findViewById(R.id.button_View_Playlist);
        buttonViewPlaylist.setOnClickListener(this);
        buttonViewPending = (Button) findViewById(R.id.button_View_Pending);
        buttonViewPending.setOnClickListener(this);
        setButtonVisibility();
    }

    private void setButtonVisibility() {
        // get admin from Shared Preferences
        SharedPreferences userPreferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
        boolean useradmin = userPreferences.getBoolean("userAdmin", false);
        // if admin show remove song button and hide vote buttons
        if (useradmin) {
            buttonViewPending.setVisibility(View.VISIBLE);
            buttonRequestApproval.setVisibility(View.INVISIBLE);
            // if NOT admin show vote buttons and hide remove song button
        } else {
            buttonViewPending.setVisibility(View.INVISIBLE);
            buttonRequestApproval.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonViewPlaylist){
            // Create intent to display song details
            Intent dispPlayList = new Intent(this, PlayListActivity.class);

            // Launch the intent
            startActivity(dispPlayList);
        }

        if (v == buttonRequestApproval){
            Log.v(TAG, "Request Approval selected");
            // Create intent to display song details
            Intent dispSongRequest = new Intent(this, RequestSubmission.class);
            // Launch the intent
            startActivity(dispSongRequest);
        }

        if (v == buttonViewPending){
            Log.v(TAG, "View Pending Approval List selected");
            // create intent to display pending approval requests
            Intent dispPendingApproval = new Intent(this, PendingApproval.class);
            // launch the intent
            startActivity(dispPendingApproval);
        }
    }
}
