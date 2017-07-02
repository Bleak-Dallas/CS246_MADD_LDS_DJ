package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Interface for users to request new songs be reviewed for addition to the catalog of approved music
 *
 * @author Damon Simpkinson
 * @since 7/1/2017.
 */

public class RequestSubmission extends AppCompatActivity implements View.OnClickListener {
    private Button buttonReturnToCatalog;
    private Button buttonSubmitRequest;
    private Song _song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_submission);

        // create the spinner for genre selection.  This is the only field that is not customized by the user
        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);

        // set on click listeners for buttons
        buttonReturnToCatalog = (Button) findViewById(R.id.button_Return_To_Catalog_from_song);
        buttonReturnToCatalog.setOnClickListener(this);
        buttonSubmitRequest = (Button) findViewById(R.id.button_Submit_Request);
        buttonSubmitRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == buttonReturnToCatalog){
            // return to the catalog view
            Intent dispCatalog = new Intent(this, CatalogActivity.class);
            startActivity(dispCatalog);
        }

        if (v == buttonSubmitRequest){
            Toast.makeText(RequestSubmission.this, "Request Submitted", Toast.LENGTH_SHORT).show();

        }
    }
}
