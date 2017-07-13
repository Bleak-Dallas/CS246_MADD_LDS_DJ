package edu.byui.maddldsdj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * @author Damon Simpkinson
 * @since 7/10/2017
 * UI for Song items awaiting approval.
 */

public class PendingApproval extends MenuActivity implements View.OnClickListener {
    private final static String TAG = "PendingApproval";

    // button for navigation
    private Button buttonReturnToCatalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_approval);

        // initialize buttons and set listeners
        buttonReturnToCatalog = (Button) findViewById(R.id.button_Return_To_Catalog);
        buttonReturnToCatalog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == buttonReturnToCatalog) {
            // Create intent to display song details
            Intent dispPlayList = new Intent(this, CatalogActivity.class);

            // Launch the intent
            startActivity(dispPlayList);
        }
    }
}
