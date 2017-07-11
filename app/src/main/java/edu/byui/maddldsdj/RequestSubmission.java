package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Interface for users to request new songs be reviewed for addition to the catalog of approved music
 *
 * @author Damon Simpkinson
 * @since 7/1/2017.
 */

public class RequestSubmission extends MenuActivity implements View.OnClickListener {
    private static final String TAG = "RequestSubmission";
    private Button buttonReturnToCatalog;
    private Button buttonSubmitRequest;
    private Song _songSubmission;
    private Catalog _catApproved = new Catalog(FirebaseDatabase.getInstance().getReference("catalog"));
    private Catalog _catPending = new Catalog(FirebaseDatabase.getInstance().getReference("PendingRequests"));
    private Catalog _catRejected = new Catalog(FirebaseDatabase.getInstance().getReference("RejectedSubmissions"));
    private String _submissionSongGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_submission);

        // create the spinner for genre selection.  This is the only field that is not customized by the user
        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
        spinnerGenre.setOnItemSelectedListener(this);

        // set on click listeners for buttons
        buttonReturnToCatalog = (Button) findViewById(R.id.button_Return_To_Catalog_from_song);
        buttonReturnToCatalog.setOnClickListener(this);
        buttonSubmitRequest = (Button) findViewById(R.id.button_Submit_Request);
        buttonSubmitRequest.setOnClickListener(this);
    }


    private void populateRequest(){
        String submissionSongTitle;
        String submissionSongArtist;
        String submissionSongAlbum;
        EditText editTitle = (EditText) findViewById(R.id.editTextEnterTitle);
        submissionSongTitle = editTitle.getText().toString();
        EditText editArtist = (EditText) findViewById(R.id.editTextEnterArtist);
        submissionSongArtist = editArtist.getText().toString();
        EditText editAlbum = (EditText) findViewById(R.id.editTextEnterAlbum);
        submissionSongAlbum = editAlbum.getText().toString();
        _songSubmission = new Song(submissionSongTitle, submissionSongArtist, submissionSongAlbum, _submissionSongGenre, true, true);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonReturnToCatalog){
            // return to the catalog view
            Intent dispCatalog = new Intent(this, CatalogActivity.class);
            startActivity(dispCatalog);
        }

        if (v == buttonSubmitRequest){
            Log.v(TAG, "New Request Selected");
            populateRequest();
            if (null == _catApproved.find(_songSubmission)){
                _songSubmission.setApproved(false);
                if (null == _catRejected.find(_songSubmission)) {
                    _songSubmission.setReviewed(false);
                    if (null == _catPending.find(_songSubmission)) {
                        _catPending.add(_songSubmission);
                        Toast.makeText(this, _songSubmission.getTitle() + " added to Pending Requests", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, _songSubmission.getTitle() + " - New song added to Pending Requests");
                    } else {
                        Toast.makeText(this, _songSubmission.getTitle() + " is already submitted for review", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, _songSubmission.getTitle() + " not submitted due to already awaiting approval");
                    }
                }
                else {
                    Toast.makeText(this, _songSubmission.getTitle() + " has been reviewed and rejected", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, _songSubmission.getTitle() + " not submitted due to already rejected");
                }
            }
            else {
                Toast.makeText(this, _songSubmission.getTitle() + " has already been approved", Toast.LENGTH_SHORT).show();
                Log.v(TAG, _songSubmission.getTitle() + "not submitted due to already in catalog");
            }
            EditText editTitle = (EditText) findViewById(R.id.editTextEnterTitle);
            editTitle.setText("");
            EditText editArtist = (EditText) findViewById(R.id.editTextEnterArtist);
            editArtist.setText("");
            EditText editAlbum = (EditText) findViewById(R.id.editTextEnterAlbum);
            editAlbum.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _submissionSongGenre = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
