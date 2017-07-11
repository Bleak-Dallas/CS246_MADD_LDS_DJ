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
    private Button buttonRequestList;
    private Button buttonViewPlaylist;
    private boolean useradmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonRequestList = (Button) findViewById(R.id.button_Request_List);
        buttonRequestList.setOnClickListener(this);
        buttonViewPlaylist = (Button) findViewById(R.id.button_View_Playlist);
        buttonViewPlaylist.setOnClickListener(this);
        setButtonVisibility();
    }

    private void setButtonVisibility() {
        // get admin from Shared Preferences
        SharedPreferences userPreferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
        useradmin = userPreferences.getBoolean("userAdmin", false);
        // if admin show View Pending Approvals button and hide Submit Request buttons
        // this is reversed for now, !useradmin is really the admin
        if (useradmin) {
            buttonRequestList.setText("View Pending Requests");
            // if NOT admin show Submit Request buttons and hide View Pending Approvals button
        } else {
            buttonRequestList.setText("Submit New Request");
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

        if (v == buttonRequestList){
            Log.v(TAG, "Request Approval selected");
            if (useradmin){
                Intent dispPendingApproval = new Intent (this, PendingApproval.class);
                startActivity(dispPendingApproval);
            }

            else {
                // Create intent to display song details
                Intent dispSongRequest = new Intent(this, RequestSubmission.class);
                // Launch the intent
                startActivity(dispSongRequest);
            }
        }
    }
}
