package edu.byui.maddldsdj;

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
    private Button buttonRequestApproval;
    private Button buttonViewPlaylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        buttonRequestApproval = (Button) findViewById(R.id.button_Request_Approval);
        buttonRequestApproval.setOnClickListener(this);
        buttonViewPlaylist = (Button) findViewById(R.id.button_View_Playlist);
        buttonViewPlaylist.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == buttonViewPlaylist){
            Toast.makeText(CatalogActivity.this, "view Playlist Selected", Toast.LENGTH_SHORT).show();
        }

        if (v == buttonRequestApproval){
            Toast.makeText(CatalogActivity.this, "Request for song approval selected", Toast.LENGTH_SHORT).show();
        }
    }
}
